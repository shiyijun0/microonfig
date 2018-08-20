

THREE.JeansData = function  JeansData(id,moduleId,moduleName,moduleSprice,moduleType,buliao,niuko,bianxian,podong,kodai,tuFRU,tuFRD,tuFLU,tuFLD,tuDR,tuDL,tuDRD,tuDLD,wenZi)
{
	this.id = id;
	this.moduleId = moduleId;
	this. moduleName = moduleName;
	this. moduleSprice = moduleSprice;
	this. moduleType = moduleType;
	this. buliao = buliao;
	this. niuko = niuko;
	this. bianxian =bianxian ;
	this. podong = podong;
	this. tuFRU = tuFRU; //前右上图案
	this. tuFRD = tuFRD; //前右下图案
	this. tuFLU = tuFLU;//前左上图案
	this. tuFLD = tuFLD;//前左下图案
	this. tuDRU  = tuDR;//后右上图案（右口袋）
	this. tuDLU =  tuDL;//后左上图案（左口袋）
	this. tuDRD = tuDRD;//后右下图案
	this. tuDLD = tuDLD;//后左下图案
	this.wenZi = wenZi;//文字
}


THREE.WordData = function WordData(color,font,content,sprice)
{
	this.color = color; //颜色
	this.font = font; //字体
	this.content = content; //内容
	this.sprice = sprice;//价格
}

 THREE.JeansSingleData =function  JeansSingleData(id,path,sprice,vec)
{
	this.id = id;
	this.path = path;
	this.sprice = sprice;
	this.vec = vec;
}
 
 THREE.ColorSingleData =function  ColorSingleData(id,path,sprice,vec,colorPath,depth,part1,part2,part3,part4,part5,part6,part7,part8)
 {
 	this.id = id;
 	this.path = path;
 	this.sprice = sprice;
 	this.vec = vec;
 	this.colorPath = colorPath;
 	this.depth = depth;//背景
 	this.part1 =part1;
 	this.part2 =part2;
 	this.part3 =part3;
 	this.part4 =part4;
 	this.part5 =part5;
 	this.part6 =part6;
 	this.part7 =part7;
 	this.part8 =part8;
 }
 

THREE.ModuleDat =  function  moduleData(x,y,z,scalex,scaley,scalez,quertion)
{
	this.x = x;
	this.y = y;
	this.z = z;
	this.scalex = scalex;
	this.scaley = scaley;
	this.scalez = scalez;
	this.quertion = quertion;
}

