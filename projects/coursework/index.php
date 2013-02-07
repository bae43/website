<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<title>Bryce Evans | Projects </title>
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		
		<link rel="stylesheet" href="../../css/lightbox.css" type="text/css" media="screen" />
		<LINK REL=StyleSheet HREF="../../css/global.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="../../css/slider.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="../../css/cards.css" TYPE="text/css">


		<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="../../js/lightbox.js"></script>
		<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-37666233-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
		

	</head>

	<body>
		<div id="header">

		</div>
		<div id='page'>

			<span id = 'name'><img id = "secimg" src="../../images/proj_small.png" ALIGN = LEFT> bryce evans </span>
			<nav  >
				<a class="nav_item" href="/index.php"> &nbsp;HOME </a> | <a class="nav_item" href="/about/index.php"> &nbsp;ABOUT </a> | <a class="nav_item" href="/education/index.php"> &nbsp;RESUME </a> | <a href="/projects/index.php" class="nav_item" style="color:#fff; text-decoration:none;"> &nbsp;PROJECTS </a>
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
						<h1> Course Projects </h1>
						<div class="l"></div>
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
							This is a list of every major final project I have completed for a class. I make
							a special effort to create a final product that is exceptional with extensions to the 
							original specification. Doing so has helped me enjoy my work more and 
							been very rewarding in numerous ways.
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
								<a href="images/misty_morning.png"  rel="lightbox[chrysalis]" title="Misty Morning" title_disp="Misty Morning" date=""><img src="images/misty_small.png" alt="" align="right" class = "main_img"/></a>

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

								</div>

								<a href="images/gears.png"  rel="lightbox[chrysalis]" title_disp="Gears" date=""><img src="images/gears_small.png" alt="" align="left" class = "main_img" style="max-width: 35%;" /></a>
								<br>
								<div class="desc">
									Working with 3D modeling for several years, I always took the raytracer for granted but building
									one and seeing the internals was really fascinating. The project was engrossing to both of us and
									we added many features that helped our raytracer stand out as being able to render near photo-realistic images.
									Our submission to the annual Raytracer Rendering Competition "Misty Morning" won first place.
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
									<a href="images/terrain.png"  rel="lightbox[graphics_prac]" title_disp="Terrain" date="Wire mesh overlayed in foreground to show geometry."><img src="images/terrain_small.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>
									<a href="images/particles.png"  rel="lightbox[graphics_prac]" title_disp="A Particle System" date=""></a>

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
										<br>
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
								<a href="images/pokejouki.png"  rel="lightbox[poke]" title_disp="Poke Jouki" date=""><img src="images/pokejouki.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>

								CS 3110: Functional Programming<span class="sep"> | </span> Fall 2012
								<div class="info">

									Language: OCaml
									<br>
									Partner: Swathi Jayevel &lt;sj336&gt;
									<br>
									<br>
								</div>
								<div>

									<div class="desc">

										Poke Jouki is based off of Pokemon Stadium from the late '90s. 
										Each team selects "steammon" from a list and then take turns 
										picking attacks and using items in order to knock out the other player's steammon
										and win. The catch is that only an AI program can be submitted, that decides which
										move to make each turn. Creating strong strategies and finding efficient implementations
										of them was very fun. 
										<br /> 
										<br /> 
										The game is built with a Java Swing GUI (which was given) and  an OCaml back-end.
										
										
										<br>
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
							<a name="cw" class="proj_title"><h2>Critter World</h2></a>
							<a href="images/critters_big.png"  rel="lightbox[cw]" title_disp="Critters" date=""> <img src="images/critters.png" alt="" align="right" class = "main_img" style="margin:10px;"/></a>

							<div class="body">
								CS 2112: OOP &amp; Data Structures [Honors]<span class="sep"> | </span> Spring 2012

								<div class="info">

									Language: Java
									<br>
									Partner: Nipat Tuntasood &lt;nt255&gt;
									<br>
									<div style="float:left">
										Awards:
									</div>
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
										<a href="images/critter.gif"  id="i1" class = "flipper_img" rel="lightbox[critter]" title_disp="Critter Animation" date=""><img src="images/animation_lit.png" alt="" /></a>
										<a href="images/critter.gif"  id="i2" class = "flipper_img" rel="lightbox[asdf]" title_disp="Critter Animation" date=""><img src="images/animation.png" alt="" /></a>
										<a href="images/plants.gif"  class = "img" rel="lightbox[critter]" title_disp="Plant Animation" date=""></a>
										<a href="images/rocks.gif"  class = "img" rel="lightbox[critter]" title_disp="Rock Animation" date=""></a>
										<a href="images/carcass.gif"  class = "img" rel="lightbox[critter]" title_disp="Critter Carcass Animation" date=""></a>
										<br>
										<br>
										<br>
										<div style="height: 12px; line-height: 18px;">
											View Animations
										</div>

										<span style="font-size:14px; line-height: 3px; color:#888; height:5px;">(~5 second loadtime)</span>
									</div>

									<div id="imgs2" style = "float:right; margin-right:100px;">
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
						<a href="images/breakout_holder.png"  rel="lightbox[breakout]" title="" date="The game graphic interface. Each pixel is set manually by the program."><img src="images/breakout_holder.png" alt="" align="right" class = "main_img" style="max-width: 35%; max-height:150px;" /></a>

						ECE 1210: Modern Computing Devices<span class="sep"> | </span> Fall 2011

						<div class="info">

							Language: LC3 Assembly
							<br>
							Partner: Greg Hill &lt;gdh39&gt;
							<br>
							<br>
						</div>
						<div>

							<div class="desc">

								Breakout is the classic arcade game with a paddle, ball, and bricks to hit and destroy.

								Creating Breakout from the most absolute basics was really eye opening
								to how computers function on the lowest level. Starting from just manipulating
								memory locations to draw a square on the screen and slowly constructing a playable game
								was highly rewarding and eventually led me to switch from electrical engineering to computer
								science.
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

