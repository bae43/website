<LINK REL=StyleSheet HREF="../../../css/global.css" TYPE="text/css">
<LINK REL=StyleSheet HREF="../../../css/accordian.css" TYPE="text/css">





<script>
	function hasGetUserMedia() {
		// Note: Opera is unprefixed.
		return !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
	}

	if (hasGetUserMedia()) {
		// Good to go!

	} else {
		alert('getUserMedia() is not supported in your browser');
	}

</script>


<?php

		// <form method="GET" action="send.php">
// <input id="imgform" name="imgform" value="">
// 
// </form>


	$title = "TILES";
	$contentTitle = "&nbspWebCam Test";
	$content = <<<EXCERPT

		<script type="text/javascript">

var video;
var copy;
var copycanvas;
var draw;

var TILE_WIDTH = 24;
var TILE_HEIGHT = 24;
var TILE_CENTER_WIDTH = TILE_WIDTH/2;
var TILE_CENTER_HEIGHT = TILE_HEIGHT/2;
var SOURCERECT = {x:0, y:0, width:0, height:0};
var PAINTRECT = {x:0, y:0, width:1000, height:600};

function init(){
video = document.getElementById('sourcevid');
copycanvas = document.getElementById('sourcecopy');
copy = copycanvas.getContext('2d');
var outputcanvas = document.getElementById('output');
draw = outputcanvas.getContext('2d');
setInterval("processFrame()", 33);
}
function createTiles(){

var offsetX = TILE_CENTER_WIDTH+(PAINTRECT.width-SOURCERECT.width)/2;
var offsetY = TILE_CENTER_HEIGHT+(PAINTRECT.height-SOURCERECT.height)/2;
var y=0;
while(y < SOURCERECT.height){
var x=0;
while(x < SOURCERECT.width){
var tile = new Tile();
tile.videoX = x;
tile.videoY = y;
tile.originX = offsetX+x;
tile.originY = offsetY+y;
tile.currentX = tile.originX;
tile.currentY = tile.originY;
tiles.push(tile);
x+=TILE_WIDTH;
}
y+=TILE_HEIGHT;
}

}

var RAD = Math.PI/180;
var randomJump = false;
var tiles = [];
var debug = false;
function processFrame(){
if(!isNaN(video.duration)){
if(SOURCERECT.width == 0){
SOURCERECT = {x:0,y:0,width:video.videoWidth,height:video.videoHeight};
createTiles();
}

}

//copy tiles
copy.drawImage(video, 0, 0);
draw.clearRect(PAINTRECT.x, PAINTRECT.y,PAINTRECT.width,PAINTRECT.height);

for(var i=0; i<tiles.length; i++){
var tile = tiles[i];
if(tile.force > 0.0001){
//expand
tile.moveX *= tile.force;
tile.moveY *= tile.force;
tile.moveRotation *= tile.force;
tile.currentX += tile.moveX;
tile.currentY += tile.moveY;
tile.rotation += tile.moveRotation;
tile.rotation %= 360;
tile.force *= 0.9;
if(tile.currentX <= 0 || tile.currentX >= PAINTRECT.width){
tile.moveX *= -1;
}
if(tile.currentY <= 0 || tile.currentY >= PAINTRECT.height){
tile.moveY *= -1;
}
}else if(tile.rotation != 0 || tile.currentX != tile.originX || tile.currentY != tile.originY){

fix(tile);

}else{
tile.force = 0;
}

draw.save();
draw.translate(tile.currentX, tile.currentY);
draw.rotate(tile.rotation*RAD);
draw.drawImage(copycanvas, tile.videoX, tile.videoY, TILE_WIDTH, TILE_HEIGHT, -TILE_CENTER_WIDTH, -TILE_CENTER_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
draw.restore();
}
if(debug){
debug = false;

}
}

function fix(tile){
//contract
var diffx = (tile.originX-tile.currentX)*0.2;
var diffy = (tile.originY-tile.currentY)*0.2;
var diffRot = (0-tile.rotation)*0.2;

if(Math.abs(diffx) < 0.5){
tile.currentX = tile.originX;
}else{
tile.currentX += diffx;
}
if(Math.abs(diffy) < 0.5){
tile.currentY = tile.originY;
}else{
tile.currentY += diffy;
}
if(Math.abs(diffRot) < 0.5){
tile.rotation = 0;
}else{
tile.rotation += diffRot;
}

}

function explode(x, y){
for(var i=0; i<tiles.length; i++){
var tile = tiles[i];

var xdiff = tile.currentX-x;
var ydiff = tile.currentY-y;
var dist = Math.sqrt(xdiff*xdiff + ydiff*ydiff);

var randRange = 220+(Math.random()*30);
var range = randRange-dist;
var force = 2.5*(range/randRange);
if(force > tile.force){
tile.force = force;
var radians = Math.atan2(ydiff, xdiff);
tile.moveX = Math.cos(radians);
tile.moveY = Math.sin(radians);
tile.moveRotation = 0.5-force;
}
}
tiles.sort(zindexSort);
processFrame();
}

function push(x, y){
for(var i=0; i<tiles.length; i++){
var tile = tiles[i];

var xdiff = tile.currentX-x;
var ydiff = tile.currentY-y;
var dist = Math.sqrt(xdiff*xdiff + ydiff*ydiff);

var randRange = 40+(Math.random()*30);
var range = randRange-(dist);
var force = 0;
if(dist < 120){

force = .1 + 2 * (range/randRange);
}
if(force > tile.force){
tile.force = force;
var radians = Math.atan2(ydiff, xdiff);
tile.moveX = Math.cos(radians);
tile.moveY = Math.sin(radians);
tile.moveRotation = force/2; //0.5; //1/dist; //0.5-Math.random();
}
}
tiles.sort(zindexSort);
processFrame();
}
function zindexSort(a, b){
return (a.force-b.force);
}

function dropBomb(evt, obj){
					
					
					
// function snapshot() {
//   
//    
  		// vsrc = document.getElementById("output");
				// var screeny = vsrc.toDataURL(); 
				// document.getElementById("imgform").value = screeny;
// }
// snapshot();
		
var posx = 0;
var posy = 0;
var e = evt || window.event;
if (e.pageX || e.pageY){
posx = e.pageX;
posy = e.pageY;
}else if (e.clientX || e.clientY) {
posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
}
var canvasX = posx-obj.offsetLeft;
var canvasY = posy-obj.offsetTop;
explode(canvasX, canvasY);
}

function drag(evt, obj){
var posx = 0;
var posy = 0;
var e = evt || window.event;
if (e.pageX || e.pageY){
posx = e.pageX;
posy = e.pageY;
}else if (e.clientX || e.clientY) {
posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
}
var canvasX = posx-obj.offsetLeft;
var canvasY = posy-obj.offsetTop;
push(canvasX, canvasY);
}

function Tile(){
this.originX = 0;
this.originY = 0;
this.currentX = 0;
this.currentY = 0;
this.rotation = 0;
this.force = 0;
this.z = 0;
this.moveX= 0;
this.moveY= 0;
this.moveRotation = 0;

this.videoX = 0;
this.videoY = 0;
}

/*
getPixel
return pixel object {r,g,b,a}
*/
function getPixel(imageData, x, y){
var data = imageData.data;
var pos = (x + y * imageData.width) * 4;
return {r:data[pos], g:data[pos+1], b:data[pos+2], a:data[pos+3]}
}
/*
setPixel
set pixel object {r,g,b,a}
*/
function setPixel(imageData, x, y, pixel){
var data = imageData.data;
var pos = (x + y * imageData.width) * 4;
data[pos] = pixel.r;
data[pos+1] = pixel.g;
data[pos+2] = pixel.b;
data[pos+3] = pixel.a;
}
/*
copyPixel
faster then using getPixel/setPixel combo
*/
function copyPixel(sImageData, sx, sy, dImageData, dx, dy){
var spos = (sx + sy * sImageData.width) * 4;
var dpos = (dx + dy * dImageData.width) * 4;
dImageData.data[dpos] = sImageData.data[spos];     //R
dImageData.data[dpos+1] = sImageData.data[spos+1]; //G
dImageData.data[dpos+2] = sImageData.data[spos+2]; //B
dImageData.data[dpos+3] = sImageData.data[spos+3]; //A
}
		</script>

	</head>

	<body onload="init()" style="margin:0px;">

		<div style="display:none">
			<video id="sourcevid" autoplay="true" loop="true"></video>
			<canvas id="sourcecopy" width="640" height="360"></canvas>
		</div>
		<div>

			<canvas id="output" width="1000" height="600" onmousemove = "drag(event,this)" onmousedown="dropBomb(event, this)"></canvas>

		</div>

		<script type="text/javascript">
			var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
			document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
		</script>
		<script type="text/javascript">
			try {
				var pageTracker = _gat._getTracker("UA-15981974-1");
				pageTracker._trackPageview();
			} catch(err) {
			}
		</script>

		<script>
			var onFailSoHard = function(e) {
				console.log('Reeeejected!', e);
			};

			window.URL = window.URL || window.webkitURL;
			navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

			var video = document.getElementById('sourcevid');

			if (navigator.getUserMedia) {
				navigator.getUserMedia({
					video : true
				}, function(stream) {
					video.src = window.URL.createObjectURL(stream);

				}, onFailSoHard);
			} else {
				alert("error, can't load");

			}

		</script>
		

		
EXCERPT;



$to = "bae43@cornell.edu";
$from = "BAE43";
$subject = "PHP E-Mail Test";
$text = "someone is messin' with them tiles";

// $image = "asdf";//$_GET["imgform"];
// 
// $message = "<html><head>"+$text+"</head><body>";
// $message .= base64_encode($image);

$headers = "From: $from";
$headers .= "Content-type: text/html";

mail($to, $subject, $text);
	include '../../../template.php';
?>


