<LINK REL=StyleSheet HREF="../../../css/global.css" TYPE="text/css">
<LINK REL=StyleSheet HREF="../../../css/accordian.css" TYPE="text/css">


<style>
	video {
		width: 307px;
		height: 250px;
		background: rgba(0,0,0,0.2);
		border: 1px solid #333;
	}
	.grayscale {
		-webkit-filter: grayscale(1);
	}
	.sepia {
		-webkit-filter: sepia(1);
	}
	.blur {
		-webkit-filter: blur(3px);
	}
	.contrast {
		-webkit-filter: contrast(10);
	}
	.invert {
		-webkit-filter: invert(1);
	}

</style>


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

	$title = "PROJECTS";
	$contentTitle = "&nbspWebCam Filter Test";
	$content = <<<EXCERPT

<br />


<video autoplay id="v"></video>
<div id="caption">
CSS Filter: None
</div>
<canvas id="c"></canvas>
<div id="c_caption">
Canvas Filter
</div>
<script>
	var onFailSoHard = function(e) {
		console.log('Reeeejected!', e);
	};

	window.URL = window.URL || window.webkitURL;
	navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

	var video = document.getElementById('v');
	//var video = document.querySelector('video');

	if (navigator.getUserMedia) {
		navigator.getUserMedia({
			video : true
		}, function(stream) {
			video.src = window.URL.createObjectURL(stream);
		}, onFailSoHard);
	} else {
		alert("error, can't load");
		video.src = 'somevideo.webm';
		// fallback.
	}

	var idx = 0;
	var filters = ['grayscale', 'sepia', 'blur', 'invert', ''];

	function changeFilter(e) {

		var el = e.target;
		el.className = '';
		var effect = filters[idx++ % filters.length];
		// loop through filters.
		if (effect) {
			el.classList.add(effect);

			document.getElementById("caption").innerHTML = ("Filter: " + el.className);

		}
	}


	document.getElementById('v').addEventListener('click', changeFilter, false); 
</script>
<script type="text/javascript" src="filter_purp.js"></script>
EXCERPT;

	include '../../../template.php';
?>


