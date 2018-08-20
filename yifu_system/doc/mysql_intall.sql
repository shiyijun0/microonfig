-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id 			int(11) 		not null auto_increment    comment '部门id',
  parent_id 		int(11) 		default 0 			       comment '父部门id',
  dept_name 		varchar(30) 	default '' 				   comment '部门名称',
  order_num 		int(4) 			default 0 			       comment '显示顺序',
  leader            varchar(20)     default ''                 comment '负责人',
  phone             varchar(20)     default ''                 comment '联系电话',
  email             varchar(20)     default ''                 comment '邮箱',
  status 			int(1) 			default 0 				   comment '部门状态:0正常,1停用',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    timestamp       default current_timestamp  comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       timestamp       default current_timestamp  comment '更新时间',
  primary key (dept_id)
) engine=innodb auto_increment=200 default charset=utf8;

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept values(100,  0,   'JWK公司', 0, '马云', '15011112221', 'ry@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(101,  100, '研发部门', 1, '马研', '15011112222', 'ry@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(102,  100, '市场部门', 2, '马市', '15011112223', 'ry@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(103,  100, '测试部门', 3, '马测', '15011112224', 'ry@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(104,  100, '财务部门', 4, '马财', '15011112225', 'ry@qq.com', 1, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(105,  100, '运维部门', 5, '马运', '15011112226', 'ry@qq.com', 1, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(106,  101, '研发一部', 1, '马一', '15011112227', 'ry@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(107,  101, '研发二部', 2, '马二', '15011112228', 'ry@qq.com', 1, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(108,  102, '市场一部', 1, '马一', '15011112229', 'ry@qq.com', 0, 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_dept values(109,  102, '市场二部', 2, '马二', '15011112210', 'ry@qq.com', 1, 'admin', '2018-03-01', 'admin', '2018-03-01');


-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id 			int(11) 		not null auto_increment    comment '用户ID',
  dept_id 			int(20) 		default null			   comment '部门ID',
  login_name 		varchar(30) 	default '' 				   comment '登录账号',
  user_name 		varchar(30) 	default '' 				   comment '用户昵称',
  email  			varchar(100) 	default '' 				   comment '用户邮箱',
  phonenumber  		varchar(20) 	default '' 				   comment '手机号码',
  password 			varchar(100) 	default '' 				   comment '密码',
  salt 				varchar(100) 	default '' 				   comment '盐加密',
  user_type         char(1)         default 'N'                comment '类型:Y默认用户,N非默认用户',
  status 			int(1) 			default 0 				   comment '帐号状态:0正常,1禁用',
  refuse_des 		varchar(500) 	default '' 				   comment '拒绝登录描述',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    timestamp       default current_timestamp  comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       timestamp       default current_timestamp  comment '更新时间',
  primary key (user_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user values(1, 106, 'admin', '管理员', 'xxx@qq.com', '15088888888', '172eee54aa664e9dd0536b063796e54e', '', 'Y', 0, '维护中', 'admin', '2018-03-01', 'admin', '2018-03-01');
insert into sys_user values(2, 108, 'ceshi',    '测试', 'xxx@163.com',      '15288888888', '2f59d63eddd54f3977d6fe25aec8b2bc', '', 'N', 1, '锁定中', 'admin', '2018-03-01', 'admin', '2018-03-01');


-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
	post_code     varchar(64)     not null  comment '岗位编码',
	post_name     varchar(100)    not null  comment '岗位名称',
	post_sort     decimal(10)               comment '岗位排序',
	status        char(1)         not null  comment '状态（0正常 1停用）',
	create_by     varchar(64)     not null  comment '创建者',
	create_time   timestamp       not null  comment '创建时间',
	update_by     varchar(64)     not null  comment '更新者',
	update_time   timestamp       not null  comment '更新时间',
	remark        varchar(500)              comment '备注信息',
	primary key (post_code)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post values('ceo',  '董事长',    1, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_post values('se',   '项目经理',  2, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_post values('hr',   '人力资源',  3, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');
insert into sys_post values('user', '普通员工',  4, 0, 'admin', '2018-03-01', 'admin', '2018-03-01', '');


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id 			int(10) 		not null auto_increment    comment '角色ID',
  role_name 		varchar(30) 	not null 				   comment '角色名称',
  role_key 		    varchar(100) 	not null 				   comment '角色权限字符串',
  role_sort         int(10)         not null                   comment '显示顺序',
  status 			int(1) 			default 0 				   comment '角色状态:0正常,1禁用',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 		timestamp       default current_timestamp  comment '创建时间',
  update_time 		timestamp       default current_timestamp  comment '更新时间',
  update_by 		varchar(64) 	default ''			       comment '更新者',
  remark 			varchar(500) 	default '' 				   comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', '管理员',   'admin',  1,  0, 'admin', '2018-03-01', '2018-03-01', 'ceshi', '管理员');
insert into sys_role values('2', '普通角色', 'common', 2,  0, 'admin', '2018-03-01', '2018-03-01', 'ceshi', '普通角色');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id 			int(11) 		not null auto_increment    comment '菜单ID',
  menu_name 		varchar(50) 	not null 				   comment '菜单名称',
  parent_id 		int(11) 		default 0 			       comment '父菜单ID',
  order_num 		int(4) 			default null 			   comment '显示顺序',
  url 				varchar(200) 	default ''				   comment '请求地址',
  menu_type 		char(1) 		default '' 			       comment '类型:M目录,C菜单,F按钮',
  visible 			int(1) 			default 0 				   comment '菜单状态:0显示,1隐藏',
  perms 			varchar(100) 	default '' 				   comment '权限标识',
  icon 				varchar(100) 	default '' 				   comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 		timestamp       default current_timestamp  comment '创建时间',
  update_time 		timestamp       default current_timestamp  comment '更新时间',
  update_by 		varchar(64) 	default ''			       comment '更新者',
  remark 			varchar(500) 	default '' 				   comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=1000 default charset=utf8;

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu values('1', '系统管理', '0', '1', '#', 'M', '0', '', 'fa fa-gear',         'admin', '2018-03-01', '2018-03-01', 'admin', '系统管理目录');
insert into sys_menu values('2', '系统监控', '0', '2', '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-01', '2018-03-01', 'admin', '系统监控目录');
insert into sys_menu values('3', '系统工具', '0', '3', '#', 'M', '0', '', 'fa fa-bars',         'admin', '2018-03-01', '2018-03-01', 'admin', '系统工具目录');
-- 二级菜单
insert into sys_menu values('4',   '用户管理', '1', '1', '/system/user',        'C', '0', 'system:user:view',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '用户管理菜单');
insert into sys_menu values('5',   '角色管理', '1', '2', '/system/role',        'C', '0', 'system:role:view',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '角色管理菜单');
insert into sys_menu values('6',   '菜单管理', '1', '3', '/system/menu',        'C', '0', 'system:menu:view',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '菜单管理菜单');
insert into sys_menu values('7',   '部门管理', '1', '4', '/system/dept',        'C', '0', 'system:dept:view',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '部门管理菜单');
insert into sys_menu values('8',   '岗位管理', '1', '5', '/system/post',        'C', '0', 'system:post:view',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '岗位管理菜单');
insert into sys_menu values('9',   '参数设置', '1', '6', '/system/config',      'C', '0', 'system:config:view',       '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '参数设置菜单');
insert into sys_menu values('10',  '字典管理', '1', '7', '/system/dict',        'C', '0', 'system:dict:view',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '字典管理菜单');
insert into sys_menu values('11',  '操作日志', '2', '1', '/monitor/operlog',    'C', '0', 'monitor:operlog:view',     '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '操作日志菜单');
insert into sys_menu values('12',  '登录日志', '2', '2', '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view',  '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '登录日志菜单');
insert into sys_menu values('13',  '在线用户', '2', '3', '/monitor/online',     'C', '0', 'monitor:online:view',      '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '在线用户菜单');
insert into sys_menu values('14',  '数据监控', '2', '4', '/monitor/data',       'C', '0', 'monitor:data:view',        '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '数据监控菜单');
insert into sys_menu values('15',  '代码生成', '3', '1', '/tool/gen',           'C', '0', 'tool:gen:view',            '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '数据监控菜单');
insert into sys_menu values('16',  '定时任务', '3', '2', '/tool/timer',         'C', '0', 'tool:timer:view',          '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '定时任务菜单');
-- 用户管理按钮
insert into sys_menu values('17', '用户查询', '4', '1', '/system/user/list',        'F', '0', 'system:user:list',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('18', '用户新增', '4', '2', '/system/user/add',         'F', '0', 'system:user:add',          '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('19', '用户修改', '4', '3', '/system/user/edit',        'F', '0', 'system:user:edit',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('20', '用户删除', '4', '4', '/system/user/remove',      'F', '0', 'system:user:remove',       '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('21', '用户保存', '4', '5', '/system/user/save',        'F', '0', 'system:user:save',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('22', '批量删除', '4', '6', '/system/user/batchRemove', 'F', '0', 'system:user:batchRemove',  '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('23', '重置密码', '4', '7', '/system/user/resetPwd',    'F', '0', 'system:user:resetPwd',     '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
-- 角色管理按钮
insert into sys_menu values('24', '角色查询', '5', '1', '/system/role/list',        'F', '0', 'system:role:list',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('25', '角色新增', '5', '2', '/system/role/add',         'F', '0', 'system:role:add',          '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('26', '角色修改', '5', '3', '/system/role/edit',        'F', '0', 'system:role:edit',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('27', '角色删除', '5', '4', '/system/role/remove',      'F', '0', 'system:role:remove',       '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('28', '角色保存', '5', '5', '/system/role/save',        'F', '0', 'system:role:save',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('29', '批量删除', '5', '6', '/system/role/batchRemove', 'F', '0', 'system:role:batchRemove',  '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
-- 菜单管理按钮
insert into sys_menu values('30', '菜单查询', '6', '1', '/system/menu/list',        'F', '0', 'system:menu:list',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('31', '菜单新增', '6', '2', '/system/menu/add',         'F', '0', 'system:menu:add',          '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('32', '菜单修改', '6', '3', '/system/menu/edit',        'F', '0', 'system:menu:edit',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('33', '菜单删除', '6', '4', '/system/menu/remove',      'F', '0', 'system:menu:remove',       '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('34', '菜单保存', '6', '5', '/system/menu/save',        'F', '0', 'system:menu:save',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
-- 部门管理按钮
insert into sys_menu values('35', '部门查询', '7', '1', '/system/dept/list',        'F', '0', 'system:dept:list',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('36', '部门新增', '7', '2', '/system/dept/add',         'F', '0', 'system:dept:add',          '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('37', '部门修改', '7', '3', '/system/dept/edit',        'F', '0', 'system:dept:edit',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('38', '部门删除', '7', '4', '/system/dept/remove',      'F', '0', 'system:dept:remove',       '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('39', '部门保存', '7', '5', '/system/dept/save',        'F', '0', 'system:dept:save',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
-- 字典管理按钮
insert into sys_menu values('40', '字典查询', '10', '1', '/system/dict/list',        'F', '0', 'system:dict:list',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('41', '字典新增', '10', '2', '/system/dict/add',         'F', '0', 'system:dict:add',          '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('42', '字典修改', '10', '3', '/system/dict/edit',        'F', '0', 'system:dict:edit',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('43', '批量删除', '10', '4', '/system/dict/batchRemove', 'F', '0', 'system:dict:batchRemove',  '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('44', '字典保存', '10', '5', '/system/dict/save',        'F', '0', 'system:dict:save',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
-- 操作日志按钮
insert into sys_menu values('45', '操作查询', '11', '1', '/monitor/operlog/list',            'F', '0', 'monitor:operlog:list',            '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('46', '批量删除', '11', '2', '/monitor/operlog/batchRemove',     'F', '0', 'monitor:operlog:batchRemove',     '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('47', '详细信息', '11', '3', '/monitor/operlog/detail',          'F', '0', 'monitor:operlog:detail',          '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
-- 登录日志按钮
insert into sys_menu values('48', '登录查询', '12', '1', '/monitor/logininfor/list',         'F', '0', 'monitor:logininfor:list',         '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('49', '批量删除', '12', '2', '/monitor/logininfor/batchRemove',  'F', '0', 'monitor:logininfor:batchRemove',  '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
-- 在线用户按钮
insert into sys_menu values('50', '在线查询', '13', '1', '/monitor/online/list',             'F', '0', 'monitor:online:list',             '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('51', '批量强退', '13', '2', '/monitor/online/batchForceLogout', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_menu values('52', '单条强退', '13', '3', '/monitor/online/forceLogout',      'F', '0', 'monitor:online:forceLogout',      '#', 'admin', '2018-03-01', '2018-03-01', 'admin', '');


-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id 	int(11) not null comment '用户ID',
  role_id 	int(11) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id 	int(11) not null comment '角色ID',
  menu_id 	int(11) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('1', '1');
insert into sys_role_menu values ('1', '2');
insert into sys_role_menu values ('1', '3');
insert into sys_role_menu values ('1', '4');
insert into sys_role_menu values ('1', '5');
insert into sys_role_menu values ('1', '6');
insert into sys_role_menu values ('1', '7');
insert into sys_role_menu values ('1', '8');
insert into sys_role_menu values ('1', '9');
insert into sys_role_menu values ('1', '10');
insert into sys_role_menu values ('1', '11');
insert into sys_role_menu values ('1', '12');
insert into sys_role_menu values ('1', '13');
insert into sys_role_menu values ('1', '14');
insert into sys_role_menu values ('1', '15');
insert into sys_role_menu values ('1', '16');
insert into sys_role_menu values ('1', '17');
insert into sys_role_menu values ('1', '18');
insert into sys_role_menu values ('1', '19');
insert into sys_role_menu values ('1', '20');
insert into sys_role_menu values ('1', '21');
insert into sys_role_menu values ('1', '22');
insert into sys_role_menu values ('1', '23');
insert into sys_role_menu values ('1', '24');
insert into sys_role_menu values ('1', '25');
insert into sys_role_menu values ('1', '26');
insert into sys_role_menu values ('1', '27');
insert into sys_role_menu values ('1', '28');
insert into sys_role_menu values ('1', '29');
insert into sys_role_menu values ('1', '30');
insert into sys_role_menu values ('1', '31');
insert into sys_role_menu values ('1', '32');
insert into sys_role_menu values ('1', '33');
insert into sys_role_menu values ('1', '34');
insert into sys_role_menu values ('1', '35');
insert into sys_role_menu values ('1', '36');
insert into sys_role_menu values ('1', '37');
insert into sys_role_menu values ('1', '38');
insert into sys_role_menu values ('1', '39');
insert into sys_role_menu values ('1', '40');
insert into sys_role_menu values ('1', '41');
insert into sys_role_menu values ('1', '42');
insert into sys_role_menu values ('1', '43');
insert into sys_role_menu values ('1', '44');
insert into sys_role_menu values ('1', '45');
insert into sys_role_menu values ('1', '46');
insert into sys_role_menu values ('1', '47');
insert into sys_role_menu values ('1', '48');
insert into sys_role_menu values ('1', '49');
insert into sys_role_menu values ('1', '50');
insert into sys_role_menu values ('1', '51');
insert into sys_role_menu values ('1', '52');

-- ----------------------------
-- 8、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id 			int(11) 		not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  action            varchar(100)    default ''                 comment '功能请求',
  method            varchar(100)    default ''                 comment '方法名称',
  channel           varchar(20)     default ''                 comment '来源渠道',
  login_name 	    varchar(50)     default '' 		 	 	   comment '登录账号',
  dept_name 		varchar(50)     default '' 		 	 	   comment '部门名称',
  oper_url 		    varchar(255) 	default '' 				   comment '请求URL',
  oper_ip 			varchar(30) 	default '' 				   comment '主机地址',
  oper_param 		varchar(255) 	default '' 				   comment '请求参数',
  status 			int(1) 		    default 0				   comment '操作状态 0正常 1异常',
  error_msg 		varchar(2000) 	default '' 				   comment '错误消息',
  oper_time 		timestamp       default current_timestamp  comment '操作时间',
  primary key (oper_id)
) engine=innodb auto_increment=100 default charset=utf8;

insert into sys_oper_log values(1, '监控管理', '在线用户-强退用户', 'com.system.project.monitor.online.controller.UserOnlineController()', 'web', 'admin', '研发部门', 'delete.do?id=1', '127.0.0.1', 'JSON参数', 0, '错误描述', '2018-03-01');


-- ----------------------------
-- 9、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
	dict_id          int(11) 		 not null auto_increment    comment '字典主键',
	dict_name        varchar(100)    default ''                 comment '字典名称',
	dict_type        varchar(100)    default ''                 comment '字典类型',
    status 			 int(1) 		 default 0				    comment '状态（0正常 1禁用）',
    create_by        varchar(64)     default ''                 comment '创建者',
    create_time      timestamp       default current_timestamp  comment '创建时间',
    update_time      timestamp       default current_timestamp  comment '更新时间',
    update_by        varchar(64) 	 default ''			        comment '更新者',
    remark 	         varchar(500) 	 default '' 				comment '备注',
	primary key (dict_id),
	unique (dict_type)
) engine=innodb auto_increment=100 default charset=utf8;

insert into sys_dict_type values(1, '银行列表', 'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '银行数据列表');
insert into sys_dict_type values(2, '支付通道', 'sys_pay_code',  0, 'admin', '2018-03-01', '2018-03-01', 'admin', '支付通道列表');


-- ----------------------------
-- 10、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
	dict_code        int(11) 		 not null auto_increment    comment '字典编码',
	dict_sort        int(4)          default 0                  comment '字典排序',
	dict_label       varchar(100)    default ''                 comment '字典标签',
	dict_value       varchar(100)    default ''                 comment '字典键值',
	dict_type        varchar(100)    default ''                 comment '字典类型',
    status 			 int(1) 		 default 0				    comment '状态（0正常 1禁用）',
    create_by        varchar(64)     default ''                 comment '创建者',
    create_time      timestamp       default current_timestamp  comment '创建时间',
    update_time      timestamp       default current_timestamp  comment '更新时间',
    update_by        varchar(64) 	 default ''			        comment '更新者',
    remark 	         varchar(500) 	 default '' 				comment '备注',
	primary key (dict_code)
) engine=innodb auto_increment=100 default charset=utf8;

insert into sys_dict_data values(1,  1, '工商银行', '01',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(2,  2, '建设银行', '02',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(3,  3, '农业银行', '03',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(4,  4, '光大银行', '04',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(5,  5, '兴业银行', '05',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', ''); 
insert into sys_dict_data values(6,  6, '中国银行', '06',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(7,  7, '平安银行', '07',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(8,  8, '招商银行', '08',  'sys_bank_code', 0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(9,  1, '微信支付', 'WX',  'sys_pay_code',  0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(10, 2, '支付宝',   'ZFB', 'sys_pay_code',  0, 'admin', '2018-03-01', '2018-03-01', 'admin', ''); 
insert into sys_dict_data values(11, 3, 'QQ支付',   'JD',  'sys_pay_code',  0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');
insert into sys_dict_data values(12, 4, '京东支付', 'QQ',  'sys_pay_code',  0, 'admin', '2018-03-01', '2018-03-01', 'admin', '');

-- ----------------------------
-- 11、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id 		int(11) 	 not null auto_increment   comment '访问ID',
  login_name 	varchar(50)  default '' 			   comment '登录账号',
  ipaddr 		varchar(50)  default '' 			   comment '登录IP地址',
  browser  		varchar(50)  default '' 			   comment '浏览器类型',
  os      		varchar(50)  default '' 			   comment '操作系统',
  status 		int(1) 		 default 0 			 	   comment '登录状态 0成功 1失败',
  msg      		varchar(255) default '' 			   comment '提示消息',
  login_time 	timestamp    default current_timestamp comment '访问时间',
  primary key (info_id)
) engine=innodb auto_increment=100 default charset=utf8;

insert into sys_logininfor values(1, 'admin', '127.0.0.1', 'Chrome 45', 'Windows 7', 0, '登录成功' ,'2018-03-01');


-- ----------------------------
-- 12、在线用户记录
-- ----------------------------
drop table if exists sys_user_online;
create table sys_user_online (
  sessionId 	    varchar(50)  default ''              	comment '用户会话id',
  login_name 	    varchar(50)  default '' 		 	 	comment '登录账号',
  dept_name 		varchar(50)  default '' 		 	 	comment '部门名称',
  ipaddr 		    varchar(50)  default '' 			 	comment '登录IP地址',
  browser  		    varchar(50)  default '' 			 	comment '浏览器类型',
  os      		    varchar(50)  default '' 			 	comment '操作系统',
  status      	    varchar(10)  default '' 			 	comment '在线状态on_line在线off_line离线',
  start_timestsamp 	timestamp    default current_timestamp  comment 'session创建时间',
  last_access_time  timestamp    default current_timestamp  comment 'session最后访问时间',
  expire_time 	    int(5) 		 default 0 			 	    comment '超时时间，单位为分钟',
  primary key (sessionId)
) engine=innodb default charset=utf8;

insert into sys_user_online(sessionId, login_name, dept_name, ipaddr, browser, os, status) 
values('c3b252c3-2229-4be4-a5f7-7aba4b0c314c', 'admin', '研发部门', '127.0.0.1', 'Chrome 45', 'Windows 7', 'on_line');

-- 用户部门表
SELECT * FROM sys_dept;

-- 用户信息表
SELECT * FROM sys_user;

-- 岗位信息表
SELECT * FROM sys_post;

-- 角色信息表
SELECT * FROM sys_role;

-- 菜单信息表
SELECT * FROM sys_menu;

-- 用户和角色关联表  用户N-1角色
SELECT * FROM sys_user_role;

-- 角色和菜单关联表  角色1-N菜单
SELECT * FROM sys_role_menu;

-- 操作日志管理表
SELECT * FROM oper_log;

-- 字典类型表
SELECT * FROM sys_dict_type;

-- 字典数据表
SELECT * FROM sys_dict_data;

-- 系统访问日志情况信息
SELECT * FROM sys_logininfor;

-- 在线用户信息
SELECT * FROM sys_user_online;
