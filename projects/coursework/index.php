<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<title>Bryce Evans | PROJECTS </title>
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<LINK REL=StyleSheet HREF="../../css/global.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="../../css/slider.css" TYPE="text/css">
		<link rel="stylesheet" href="../../css/lightbox.css" type="text/css" media="screen" />

		<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="../../js/lightbox.js"></script>

		<style>
			.proj_title {
				/*background: #181818;*/
			}
			.info {
				position: relative;
				left: 40px;
				font-size: 16px;
				line-height: 18px;
			}
			.desc {
				/*max-width: 49%;*/
				/*float: left;*/
			}
			.main_img {
				max-width: 49%;
				border: solid 1px #000;
				-moz-box-shadow: 3px 3px 4px #000;
				-webkit-box-shadow: 3px 3px 4px #000;
				box-shadow: 3px 3px 4px #000;
				margin: 10px 20px;
			}

			.flipper_img {
				display: block;
				position: absolute;
				top: 0;
				left: 10px;
			}

			.flipper_img2 {
				display: block;
				position: absolute;
				top: 0;
				right: 23px;
			}

			#imgs {
				position: relative;
				left: 10%;
				top: 0;
				width: 128px;
				text-align: center;
			}
			#imgs2 {
				position: relative;
				right: 45%;
				top: 0;
				width: 128px;
				height: 100%;
				text-align: center;
			}

			li {
				list-style-image: url(images/bullet.png);
			}

			.lb-outerContainer {

				background-color: black;
			}
		</style>
	</head>

	<body>
		<div id="header">

		</div>
		<div id='page' style="width: 1000px; border:solid black 1px;">

			<span id = 'name'><img id = "secimg" src="../../images/proj_small.png" ALIGN = LEFT> bryce evans </span>
			<nav style="float:right; color:#ccc;" >
				<a href="/Website/about/index.php"> About </a> | <a href="/Website/education/index.php"> Resume </a> | <a href="/Website/projects/index.php" style="color:#fff; text-decoration:none;"> Projects </a>
			</nav>

			<!-- 			<div >
			<div id='curPage'>
			<span><h3><b>&nbsp;Projects></b></h3></span>
			</div>

			</div> -->
			<br>

			<div id='content'>

				<!-- -------------------------------------------------------------------------------------  -->
				<div>
					<br/>
					<center>
						<h2> Course Projects </h2>
					</center>
					<br>
					Navigation:
					<br>
					<a href="#chrysalis" >Chrysalis Renderer [CS 4620]</a>
					<br>
					<a href="#cw" >Critter World [CS 2112]</a>
					<br>
					<a href="#maze_solver" >Maze Solver [CS 1114]</a>
				</div>
				<div>
					<br />
					
					<div  class="proj_title">
						<a name="chrysalis"><h2>Chrysalis Renderer</h2></a>
					</div>
					<div class="body">
						<!-- <img  class="main_img" src="images/misty_morning.png"  /> -->
						<a href="images/misty_morning.png"  rel="lightbox[chrysalis]" title="Misty Morning" date=""><img src="images/misty_small.png" alt="" align="right" class = "main_img"/></a>

						<div class="info">
							CS 4620: Computer Graphics I
							<br>

							Partner: John Oliver &lt;jro67&gt;
							<br>
							Awards: <a href="http://www.cs.cornell.edu/courses/CS4620/2012fa/competition/cs4620.html"> 1st place -  Class Raytracer Rendering Competition</a>
						</div>

						<div class="desc">
							<br>
							Chrysalis is a raytracing renderer that supports:
							<ul style="margin: 0">
								<li>
									Lambert and Blinn-Phong materials (plastics)
								</li>
								<li>
									Reflective "glazed" materials (metals)
								</li>
								<li>
									Transparent &amp; refractive materials (glass and water)
								</li>
								<li>
									Orthographic and perspective cameras
								</li>
								<li>
									Depth of Field (DOF)
								</li>
								<li>
									Importing .obj models
								</li>
								<li>
									UV maps and texturing
								</li>

							</ul>

							The last three features listed were extensions not covered in the class, but we added for use in our submission to the class's annual Raytracer Competition. Our submission "Misty Morning" won first place.
						</div>

						<a href="images/gears.png"  rel="lightbox[chrysalis]" title="Gears" date=""><img src="images/gears_small.png" alt="" align="left" class = "main_img" style="max-width: 35%;" /></a>

						<div class="desc">
							"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
						</div>
						<div>
							<br>
							<br>
							<a name="cw" class="proj_title"><h2>Critter World</h2></a>
							<div class="info">
								CS 2112: OOP &amp; Data Structures with Java [Honors]

								<br>

								Partner: Nipat Tuntasood &lt;nt255&gt;
								<br>
								Awards:
								<div>
									High Score on Grammar Interpretation
									<br>
									Best GUI
									<br>
									"Champion Critter" - Best Critter AI
								</div>
								<br>
								
							</div>
							<br>
							A simulation of evolving artificial life. Uses an Abstract Syntax Tree (AST) to parse a grammar of a basic programming language.
							The language defines the critters actions on each turn, whether it be eat, move, or attack a neighboring enemy critter.
							<center>
								<div id="cw_options">
									<div id="imgs" style="float:left;">
										<!-- 						<img src="images/animation_lit.png" class = "flipper_img"  id="i1">
										<img src="images/animation.png" class = "flipper_img" id="i2"> -->
										<a href="images/critter.gif"  id="i1" class = "flipper_img" rel="lightbox[critter]" title="Critter Animation" date=""><img src="images/animation_lit.png" alt="" /></a>
										<a href="images/critter.gif"  id="i2" class = "flipper_img" rel="lightbox[asdf]" title="Critter Animation" date=""><img src="images/animation.png" alt="" /></a>
										<a href="images/plants.gif"  class = "img" rel="lightbox[critter]" title="Plant Animation" date=""><img src="" alt="" /></a>
										<a href="images/rocks.gif"  class = "img" rel="lightbox[critter]" title="Rock Animation" date=""><img src="" alt="" /></a>
										<a href="images/carcass.gif"  class = "img" rel="lightbox[critter]" title="Critter Carcass Animation" date=""><img src="" alt="" /></a>
										<br>
										<br>
										<br>
										<div style="height: 12px; line-height: 18px;">
											View Animations
										</div>

										<span style="font-size:14px; line-height: 3px; color:#888; height:5px;">(~5 second loadtime)</span>
									</div>

									<div id="imgs2" style = "float:right;">
										<a  id = "i3" class = "flipper_img2" href="critterworld/CritterWorld.zip"><img src="images/download_lit.png" alt="" /></a>
										<a  id = "i4" class = "flipper_img2" href="critterworld/CritterWorld.zip"><img src="images/download.png" alt="" /></a>

										<br>
										<br>
										<br>
										<div style="height: 12px; line-height: 18px;">
											Download
										</div>

										<span style="font-size:14px; line-height: 3px; color:#888; height:5px;">(~40 MB)</span>
									</div>

								</div>
							</center>
						</div>
						<script>
							$(function() {
								$('#imgs').mouseenter(function() {
									$('#i2').fadeOut('fast');

								}).mouseleave(function() {
									$('#i2').fadeIn('fast');

								});
							});

							$(function() {
								$('#imgs2').mouseenter(function() {
									$('#i4').fadeOut('fast');

								}).mouseleave(function() {
									$('#i4').fadeIn('fast');

								});
							});
						</script>
						<br>
						<br>
						<br>
						<br>
						<div>
							<br>
							<h2> Maze Solver</h2>
							<div class="info">
								CS 1114: Robotics with MatLab
								<br>
								Partner: Remi Torricinta &lt;rct66&gt;
								<br>
							</div>

							<br>
							A fast and robust maze solver made in MatLab. Open an image,
							click on the start and end points, and the solver will find the
							shortest path of white connecting them. Works on any image.
						</div>

						<!-- -------------------------------------------------------------------------------------  -->
					</div>

					<div id="footer">
						<p>
							&copy; 2012 Bryce Evans
						</p>
					</div>
				</div>

	</body>
</html>

</div>
</div>
</body>
</html>

