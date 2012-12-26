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
			body {
				background-color: #181818;
			}
			.proj_title {
				/*background: #181818;*/
			}
			.info {
				position: relative;
				left: 40px;
				font-size: 15px;
				line-height: 18px;
			}
			.desc {
				/*max-width: 49%;*/
				/*float: left;*/
				font-size: 16px;
				line-height: 22px;
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
			.proj_border {
				border: solid black 1px;
				border-radius: 4px;
				background: #222;
			}
			.cushion {
				margin: 20px;
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
			.sep {
				color: #779;
			}
		</style>
	</head>

	<body>
		<div id="header">

		</div>
		<div id='page' style="width: 1040px; border:solid black 0px; ">

			<span id = 'name'><img id = "secimg" src="../../images/proj_small.png" ALIGN = LEFT> bryce evans </span>
			<nav style="float:right; color:#ccc;" >
				<a href="/about/index.php"> About </a> | <a href="/education/index.php"> Resume </a> | <a href="/projects/index.php" style="color:#fff; text-decoration:none;"> Projects </a>
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
						<h2 style="font-size:42px;"> Course Projects </h2>
					</center>
					<br>
					<div>
						<div style="float:left">
							<span style="font-size:22px; margin:1em;">Contents:</span>
							<div style="margin-left:3em; margin-right:1em;">

								<a href="#chrysalis" >Chrysalis Renderer [CS 4620]</a>
								<br>
								<a href="#cw" >Critter World [CS 2112]</a>
								<br>
								<a href="#maze" >Maze Solver [CS 1114]</a>
							</div>
						</div>
						<div >
							Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
							ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
							laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
							voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
							cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laboru
						</div>
					</div>
				</div>
				<div>
					<br />
					<div class="proj_border ">
						<div class ="cushion" >
							<div  class="proj_title">
								<a name="chrysalis"><h2>Chrysalis Renderer</h2></a>
							</div>
							CS 4620: Computer Graphics <span class="sep">|</span> Fall 2012
							<div class="body">
								<!-- <img  class="main_img" src="images/misty_morning.png"  /> -->
								<a href="images/misty_morning.png"  rel="lightbox[chrysalis]" title="Misty Morning" date=""><img src="images/misty_small.png" alt="" align="right" class = "main_img"/></a>

								<div class="info">

									Language: Java with OpenGL (JOGL)
									<br>

									Partner: John Oliver &lt;jro67&gt;
									<br>
									Awards: <a href="http://www.cs.cornell.edu/courses/CS4620/2012fa/competition/cs4620.html"> 1st place -  Class Raytracer Rendering Competition</a>
								</div>

								<div class="desc">
									<br>
									Chrysalis is a Whitted raytracer that supports:
									<br>
									<ul style="margin: 0">
										<li>
											Lambert and Blinn-Phong materials
										</li>
										<li>
											Reflective, transparent, and refractive materials
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
									<br>

									The last three features listed were extensions not covered in the class, but we added for use in our submission to the class's annual Raytracer Competition. Our submission "Misty Morning" won first place.
								</div>

								<a href="images/gears.png"  rel="lightbox[chrysalis]" title="Gears" date=""><img src="images/gears_small.png" alt="" align="left" class = "main_img" style="max-width: 35%;" /></a>

								<div class="desc">
									"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
								</div>
								<br>

								<br>
							</div>
						</div>
					</div>
					<br>

					<div class="proj_border ">
						<div class ="cushion" >
							<div class="body">

								<a name="maze" ><h2> Scene Editor </h2></a>
								CS 4621: Computer Graphics Practicum <span class="sep"> | </span> Fall 2012
								<div class="info">

									Language:  Java with OpenGL (JOGL)
									<br>
									Partner: John Oliver &lt;jro67&gt;
									<br>
									<br>
								</div>
								<div>
									<a href="images/terrain.png"  rel="lightbox[graphics_prac]" title="Terrain" date="Wire mesh overlayed in foreground to show geometry."><img src="images/terrain_small.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>
									<a href="images/particles.png"  rel="lightbox[graphics_prac]" title="A Particle System" date=""><img src="" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>

									<div class="desc">
										The graphics practicum consisted of slowly constructing and adding new features to a scene editor throughout the semester.
										We implemented many library functions such as MIP maps and mesh primitives which me a solid understanding of the core of OpenGL.
										Just some of the other features our final program supported were:

										<ul style="margin: 0">

											<li>
												Translation,
												rotation, and scaling manipulators
											</li>
											<li>
												Particle Systems
											</li>
											<li>
												A spline editor with surfaces of revolution
											</li>
											<li>
												Keyframe animation with linear and spline interpolation
											</li>

											<li>
												Texturing and an animated lava shader from perlin noise
											</li>

										</ul>

										In addition, I also added support for multiple textures and added a terrain primitive. The terrain
										could be loaded with a height map to construct detailed geometry. It was also procedurally textured based on
										slope and height to give realistic coloration and fading of grass to steep stone cliffs.

										<br>

										<br>
									</div>

								</div>
							</div>
						</div>
					</div>

					<br>

					<div class="proj_border ">
						<div class ="cushion" >
							<div class="body">

								<a name="maze" ><h2> Poke Jouki</h2></a>
								<a href="images/xl_solved.png"  rel="lightbox[breakout]" title="A Solved Maze" date="Runtime - 4 minutes on Intel i3 2.1 GHz"><img src="images/maze_solve.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>

								CS 3110: Functional Programming<span class="sep"> | </span> Fall 2012
								<div class="info">

									Language: Ocaml
									<br>
									Partner: Swathi Jayevel &lt;sj336&gt;
									<br>
									<br>
								</div>
								<div>

									<div class="desc">

										"Lorem ipsum dolor sit amet, consectetur adipisicing elit,
										sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
										Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
										nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
										reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
										pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
										culpa qui officia deserunt mollit anim id est laborum."
										<br>
										<br>
										"Lorem ipsum dolor sit amet, consectetur adipisicing elit,
										sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
										Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
										nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
										reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
										pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
										culpa qui officia deserunt mollit anim id est laborum."
										<br>
									</div>

								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="proj_border ">
						<div class ="cushion" >
							<a name="cw" class="proj_title"><h2>Critter World</h2></a>
							<a href="images/critters_big.png"  rel="lightbox[cw]" title="Critters" date=""> <img src="images/critters.png" alt="" align="right" class = "main_img" style="margin:10px;"/></a>

							<div class="body">
								CS 2112: OOP &amp; Data Structures [Honors]<span class="sep"> | </span> Spring 2012

								<div class="info">

									Language: Java
									<br>
									Partner: Nipat Tuntasood &lt;nt255&gt;
									<br>
									<div style="float:left">Awards:</div>
									<div align="left">

									High Score on Grammar Interpretation
									<br>
									Best GUI
									<br>
									"Champion Critter" - Best Critter AI
</div>
								</div>

							</div>
							<br>
							<div class="desc">
								Critter World is a simulation of evolving artificial life. Uses an Abstract Syntax Tree (AST) to parse a grammar of a basic programming language.
								The language defines the critters actions on each turn, whether it be eat, move, or attack a neighboring enemy critter.

								Both the front end and the back end of the project involved complex problems.

								When designing Critter World, my partner Nipat and I wanted differing styles. We ended up compromising and combining an
								alien environment with a microscopic one. Our graphic interface also supported numerous features in addition to the main spec,
								including:
								<div class = "two_col">
									<ul style="float:left">
										<li>
											Splash Screen with Loading Bar
										</li>
										<li>
											Animation
										</li>
										<li>
											Sounds
										</li>
										<li>
											Zoom, Pan, bring critter to focus, etc...
										</li>

									</ul>
									<ul style="float:right">

										<li>
											Importing and Exporting Critters
										</li>
										<li>
											Importing and Exporting Saved World States
										</li>
										<li>
											Menu Bar with total of dozen options in addition to side panel controls
										</li>

									</ul>
								</div>
							</div>
							<br>
							<br>
							<br>
							<br>
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
						</div>
						</center>

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
						<br>
					</div>
				</div>
			</div>
			<br>

			<div class="proj_border ">
				<div class ="cushion" >
					<div class="body">

						<a name="maze" ><h2> Maze Solver and Generator</h2></a>
						CS 1114: Robotics<span class="sep"> | </span> Spring 2012
						<div class="info">

							Language: MatLab
							<br>
							Partner: Remi Torricinta &lt;rct66&gt;
							<br>
							<br>
						</div>
						<div>
							<a href="images/xl_solved.png"  rel="lightbox[maze]" title="A Solved Maze" date="Runtime - 4 minutes on Intel i3 2.1 GHz"><img src="images/maze_solve.png" alt="" align="left" class = "main_img" style="max-width: 35%;" /></a>

							<div class="desc">

								We were given the opportunity to use MatLab to create anything we wanted and found that efficient
								maze creation and generation was both practical and fascinating.
								The final product was fast and robust program that could solve the most complicated mazes we could find (2000x2000 grid) in a couple minutes.
								Open a black and white image, click on the start and end points, and... Done! Shortest path found.
								<br>
								<br>
								The process behind the algorithm involves first finding a convex hull around the image
								to eliminate any narrow white borders that could yeild a shorter path than the intended
								path through the maze. The black sections are then dialated to leave only a single pixel
								of white for each path. The closest connected remaining white pixel to both the start and
								the end is marked as the original marks may have been blacked by dialation.
								A breadth first search (BFS) is then run from the initial start to the end, leaving a trail of "bread crumbs"
								of the direction it came from. Once the target finish is reached, the trail left is retraced and highlighted.
								<br>
								<br>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br>

			<div class="proj_border ">
				<div class ="cushion" >
					<div class="body">

						<a name="maze" ><h2> Breakout</h2></a>
						ECE 1210: Modern Computing Devices<span class="sep"> | </span> Fall 2011 <a href="images/breakout_holder.png"  rel="lightbox[breakout]" title="A Solved Maze" date="The game graphic interface. Each pixel is set manually by the program."><img src="images/breakout_holder.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>

						<div class="info">

							Language: LC3 Assembly
							<br>
							Partner: Greg Hill &lt;gdh39&gt;
							<br>
							<br>
						</div>
						<div>

							<div class="desc">

								"Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
								Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
								nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
								reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
								pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
								culpa qui officia deserunt mollit anim id est laborum."
								<br>
								<br>
								"Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
								Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
								nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
								reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
								pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
								culpa qui officia deserunt mollit anim id est laborum."
								<br>
							</div>

						</div>
					</div>
				</div>
			</div>

			<!-- -------------------------------------------------------------------------------------  -->
		</div>
		<br>
		<br>
		<br>
		<br>

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

