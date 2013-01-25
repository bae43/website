<!doctype html>
<html lang="en">
	<head>
		<title>Bryce Evans | Home</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<LINK REL=StyleSheet HREF="css/global.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="css/main.css" TYPE="text/css">
		<script src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-37666233-1']);
			_gaq.push(['_trackPageview']);

			(function() {
				var ga = document.createElement('script');
				ga.type = 'text/javascript';
				ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(ga, s);
			})();

		</script>
	</head>
	<body>

		<div id="page">
			<div  class = "box" id = "links">
				<a href="about/index.php"> <img class="icon"  id="about" src="images/icon_about.png" alt="about"> </a>
<!-- 				<a href="about/index.php"> <img class="icon" id="about" src="images/icon_other.png" alt="projects"> </a> -->
			
				<a href="education/index.php"> <img class="icon"  id="resume" src="images/icon_courses.png" alt="coursework"> </a>
				<a href="projects/index.php"> <img class="icon" id="projects" src="images/icon_other.png" alt="projects"> </a>
				</div>

			<div class = "box" id="main">

				<br>
				<h1> bryce evans</h1>

				<p>
					<br />

					<div class = 'l'></div>
					<div style="height : 10px;"></div>

					<span id="subname"> Computer Science | Cornell University </span>
					<div style="height : 7px;"></div>

					<div class = 'l'></div>

				</p>
				<div class="separator"></div>

			</div>

		</div>

		<div  id="footer" style="position:absolute;">
			<p>
				&copy; 2012 Bryce Evans
			</p>
		</div>

		<script type="text/javascript">
			function center_vertical() {
				var new_height = (window.innerHeight - $('.box').height()) / 3 - 50;
				$('.box').css('margin-top', new_height);
			}


			$(document).ready(center_vertical);
			$(window).resize(center_vertical);

			setTimeout(function() {
				$('#page').css({
					"width" : 480
				})// Set to 0 as soon as possible – may result in flicker, but it's not hidden for users with no JS (Googlebot for instance!)
				.delay(300)// Wait for a bit so the user notices it fade in
				.css({
					"width" : 600
				});
			}, 500);



		</script>

	</body>

</html>

