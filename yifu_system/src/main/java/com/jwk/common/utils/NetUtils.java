package com.jwk.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sun.xml.internal.ws.util.UtilException;

public class NetUtils {

	public final static String LOCAL_IP = "127.0.0.1";

	/** IP v4 */
	public final static Pattern IPV4 = Pattern.compile(
			"\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

	/**
	 * 给定内容是否匹配正则
	 *
	 * @param pattern
	 *            模式
	 * @param content
	 *            内容
	 * @return 正则为null或者""则不检查，返回true，内容为null返回false
	 */
	public static boolean isMatch(Pattern pattern, String content) {
		if (content == null || pattern == null) {
			// 提供null的字符串为不匹配
			return false;
		}
		return pattern.matcher(content).matches();
	}

	public static boolean isIpv4(String value) {
		return isMatch(IPV4, value);
	}

	/**
	 * 根据long值获取ip v4地址
	 *
	 * @param longIP
	 *            IP的long表示形式
	 * @return IP V4 地址
	 */
	public static String longToIpv4(long longIP) {
		final StringBuilder sb = new StringBuilder();
		// 直接右移24位
		sb.append(String.valueOf(longIP >>> 24));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIP & 0x000000FF));
		return sb.toString();
	}

	/**
	 * 根据ip地址计算出long型的数据
	 *
	 * @param strIP
	 *            IP V4 地址
	 * @return long值
	 */
	public static long ipv4ToLong(String strIP) {
		if (isIpv4(strIP)) {
			long[] ip = new long[4];
			// 先找到IP地址字符串中.的位置
			int position1 = strIP.indexOf(".");
			int position2 = strIP.indexOf(".", position1 + 1);
			int position3 = strIP.indexOf(".", position2 + 1);
			// 将每个.之间的字符串转换成整型
			ip[0] = Long.parseLong(strIP.substring(0, position1));
			ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
			ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
			ip[3] = Long.parseLong(strIP.substring(position3 + 1));
			return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
		}
		return 0;
	}

	/**
	 * 检测本地端口可用性
	 *
	 * @param port
	 *            被检测的端口
	 * @return 是否可用
	 */
	public static boolean isUsableLocalPort(int port) {
		if (false == isValidPort(port)) {
			// 给定的IP未在指定端口范围中
			return false;
		}
		try {
			new Socket(LOCAL_IP, port).close();
			// socket链接正常，说明这个端口正在使用
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 是否为有效的端口
	 *
	 * @param port
	 *            端口号
	 * @return 是否有效
	 */
	public static boolean isValidPort(int port) {
		// 有效端口是0～65535
		return port >= 0 && port <= 0xFFFF;
	}

	/**
	 * 判定是否为内网IP<br>
	 * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
	 * 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
	 *
	 * @param ipAddress
	 *            IP地址
	 * @return 是否为内网IP
	 */
	public static boolean isInnerIP(String ipAddress) {
		boolean isInnerIp = false;
		long ipNum = NetUtils.ipv4ToLong(ipAddress);

		long aBegin = NetUtils.ipv4ToLong("10.0.0.0");
		long aEnd = NetUtils.ipv4ToLong("10.255.255.255");

		long bBegin = NetUtils.ipv4ToLong("172.16.0.0");
		long bEnd = NetUtils.ipv4ToLong("172.31.255.255");

		long cBegin = NetUtils.ipv4ToLong("192.168.0.0");
		long cEnd = NetUtils.ipv4ToLong("192.168.255.255");

		isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd)
				|| ipAddress.equals(LOCAL_IP);
		return isInnerIp;
	}

	/**
	 * 相对URL转换为绝对URL
	 *
	 * @param absoluteBasePath
	 *            基准路径，绝对
	 * @param relativePath
	 *            相对路径
	 * @return 绝对URL
	 */
	public static String toAbsoluteUrl(String absoluteBasePath, String relativePath) {
		try {
			URL absoluteUrl = new URL(absoluteBasePath);
			return new URL(absoluteUrl, relativePath).toString();
		} catch (Exception e) {
			throw new UtilException(
					String.format("To absolute url [%s] base [%s] error!", relativePath, absoluteBasePath), e);
		}
	}

	/**
	 * 隐藏掉IP地址的最后一部分为 * 代替
	 *
	 * @param ip
	 *            IP地址
	 * @return 隐藏部分后的IP
	 */
	public static String hideIpPart(String ip) {
		return new StringBuffer(ip.length()).append(ip.substring(0, ip.lastIndexOf(".") + 1)).append("*").toString();
	}

	/**
	 * 隐藏掉IP地址的最后一部分为 * 代替
	 *
	 * @param ip
	 *            IP地址
	 * @return 隐藏部分后的IP
	 */
	public static String hideIpPart(long ip) {
		return hideIpPart(longToIpv4(ip));
	}

	/**
	 * 构建InetSocketAddress<br>
	 * 当host中包含端口时（用“：”隔开），使用host中的端口，否则使用默认端口<br>
	 * 给定host为空时使用本地host（127.0.0.1）
	 *
	 * @param host
	 *            Host
	 * @param defaultPort
	 *            默认端口
	 * @return InetSocketAddress
	 */
	public static InetSocketAddress buildInetSocketAddress(String host, int defaultPort) {
		if (StringUtils.isBlank(host)) {
			host = LOCAL_IP;
		}

		String destHost = null;
		int port = 0;
		int index = host.indexOf(":");
		if (index != -1) {
			// host:port形式
			destHost = host.substring(0, index);
			port = Integer.parseInt(host.substring(index + 1));
		} else {
			destHost = host;
			port = defaultPort;
		}

		return new InetSocketAddress(destHost, port);
	}

	/**
	 * 通过域名得到IP
	 *
	 * @param hostName
	 *            HOST
	 * @return ip address or hostName if UnknownHostException
	 */
	public static String getIpByHost(String hostName) {
		try {
			return InetAddress.getByName(hostName).getHostAddress();
		} catch (UnknownHostException e) {
			return hostName;
		}
	}

	/**
	 * 获取本机所有网卡
	 *
	 * @return 所有网卡，异常返回<code>null</code>
	 * @since 3.0.1
	 */
	public static Collection<NetworkInterface> getNetworkInterfaces() {
		Enumeration<NetworkInterface> networkInterfaces = null;
		try {
			networkInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			return null;
		}
		ArrayList<NetworkInterface> collection = new ArrayList<>();
		if (null != collection && null != networkInterfaces) {
			while (networkInterfaces.hasMoreElements()) {
				collection.add(networkInterfaces.nextElement());
			}
		}
		return collection;
	}

	/**
	 * 获得本机的IP地址列表<br>
	 * 返回的IP列表有序，按照系统设备顺序
	 *
	 * @return IP地址列表 {@link LinkedHashSet}
	 */
	public static LinkedHashSet<String> localIpv4s() {
		Enumeration<NetworkInterface> networkInterfaces = null;
		try {
			networkInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			throw new UtilException(e.getMessage(), e);
		}

		if (networkInterfaces == null) {
			throw new UtilException("Get network interface error!");
		}

		final LinkedHashSet<String> ipSet = new LinkedHashSet<>();

		while (networkInterfaces.hasMoreElements()) {
			final NetworkInterface networkInterface = networkInterfaces.nextElement();
			final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			while (inetAddresses.hasMoreElements()) {
				final InetAddress inetAddress = inetAddresses.nextElement();
				if (inetAddress != null && inetAddress instanceof Inet4Address) {
					ipSet.add(inetAddress.getHostAddress());
				}
			}
		}

		return ipSet;
	}

	/**
	 * 获取本机网卡IP地址，这个地址为所有网卡中非回路地址的第一个<br>
	 * 如果获取失败调用 {@link InetAddress#getLocalHost()}方法获取。<br>
	 * 此方法不会抛出异常，获取失败将返回<code>null</code><br>
	 *
	 * 参考：http://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
	 *
	 * @return 本机网卡IP地址，获取失败返回<code>null</code>
	 * @since 3.0.7
	 */
	public static String getLocalhostStr() {
		InetAddress localhost = getLocalhost();
		if (null != localhost) {
			return localhost.getHostAddress();
		}
		return null;
	}

	/**
	 * 获取本机网卡IP地址，这个地址为所有网卡中非回路地址的第一个<br>
	 * 如果获取失败调用 {@link InetAddress#getLocalHost()}方法获取。<br>
	 * 此方法不会抛出异常，获取失败将返回<code>null</code><br>
	 *
	 * 参考：http://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
	 *
	 * @return 本机网卡IP地址，获取失败返回<code>null</code>
	 * @since 3.0.1
	 */
	public static InetAddress getLocalhost() {
		InetAddress candidateAddress = null;
		NetworkInterface iface;
		InetAddress inetAddr;
		try {
			for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces
					.hasMoreElements();) {
				iface = ifaces.nextElement();
				for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
					inetAddr = inetAddrs.nextElement();
					if (false == inetAddr.isLoopbackAddress()) {
						if (inetAddr.isSiteLocalAddress()) {
							return inetAddr;
						} else if (null == candidateAddress) {
							// 非site-local地址做为候选地址返回
							candidateAddress = inetAddr;
						}
					}
				}
			}
		} catch (SocketException e) {
			// ignore socket exception, and return null;
		}

		if (null == candidateAddress) {
			try {
				candidateAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// ignore
			}
		}

		return candidateAddress;
	}

	/**
	 * 获得本机MAC地址
	 *
	 * @return 本机MAC地址
	 */
	public static String getLocalMacAddress() {
		return getMacAddress(getLocalhost());
	}

	/**
	 * 获得指定地址信息中的MAC地址，使用分隔符“-”
	 *
	 * @param inetAddress
	 *            {@link InetAddress}
	 * @return MAC地址，用-分隔
	 */
	public static String getMacAddress(InetAddress inetAddress) {
		return getMacAddress(inetAddress, "-");
	}

	/**
	 * 获得指定地址信息中的MAC地址
	 *
	 * @param inetAddress
	 *            {@link InetAddress}
	 * @param separator
	 *            分隔符，推荐使用“-”或者“:”
	 * @return MAC地址，用-分隔
	 */
	public static String getMacAddress(InetAddress inetAddress, String separator) {
		if (null == inetAddress) {
			return null;
		}

		byte[] mac;
		try {
			mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
		} catch (SocketException e) {
			throw new UtilException(e);
		}
		if (null != mac) {
			final StringBuilder sb = new StringBuilder();
			String s;
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append(separator);
				}
				// 字节转换为整数
				s = Integer.toHexString(mac[i] & 0xFF);
				sb.append(s.length() == 1 ? 0 + s : s);
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 创建 {@link InetSocketAddress}
	 * 
	 * @param host
	 *            域名或IP地址
	 * @param port
	 *            端口
	 * @return {@link InetSocketAddress}
	 * @since 3.3.0
	 */
	public static InetSocketAddress createAddress(String host, int port) {
		return new InetSocketAddress(host, port);
	}

	/**
	 *
	 * 简易的使用Socket发送数据
	 *
	 * @param host
	 *            Server主机
	 * @param port
	 *            Server端口
	 * @param isBlock
	 *            是否阻塞方式
	 * @param data
	 *            需要发送的数据
	 * @since 3.3.0
	 */
	public static void netCat(String host, int port, boolean isBlock, ByteBuffer data) {
		try (SocketChannel channel = SocketChannel.open(createAddress(host, port))) {
			channel.configureBlocking(isBlock);
			channel.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 使用普通Socket发送数据
	 *
	 * @param host
	 *            Server主机
	 * @param port
	 *            Server端口
	 * @param data
	 *            数据
	 * @throws IOException
	 *             IO异常
	 * @since 3.3.0
	 */
	public static void netCat(String host, int port, byte[] data) {
		OutputStream out = null;
		try (Socket socket = new Socket(host, port)) {
			out = socket.getOutputStream();
			out.write(data);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// -----------------------------------------------------------------------------------------
	// Private method start
	/**
	 * 指定IP的long是否在指定范围内
	 *
	 * @param userIp
	 *            用户IP
	 * @param begin
	 *            开始IP
	 * @param end
	 *            结束IP
	 * @return 是否在范围内
	 */
	private static boolean isInner(long userIp, long begin, long end) {
		return (userIp >= begin) && (userIp <= end);
	}
	// -----------------------------------------------------------------------------------------
	// Private method end
}