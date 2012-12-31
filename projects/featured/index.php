<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<title>Bryce Evans | Projects </title>
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<LINK REL=StyleSheet HREF="../../css/global.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="../../css/slider.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="../../css/cards.css" TYPE="text/css">
		<link rel="stylesheet" href="../../css/lightbox.css" type="text/css" media="screen" />

		<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="../../js/lightbox.js"></script>

		<style>

			#side_nav_title {
				font-size: 22px;
				position: relative;
				top: -.85em;
				margin: 0 3em;
				background: #181818;
			}
		</style>
	</head>

	<body>
		<div id="header">

		</div>
		<div id='page'>

			<span id = 'name'><img id = "secimg" src="../../images/proj_small.png" ALIGN = LEFT> bryce evans </span>
			<nav>
				<a class="nav_item" href="/index.php"> &nbsp;HOME </a>
				|
				<a class="nav_item" href="/about/index.php" href="/about/index.php" > &nbsp;ABOUT </a>
				|
				<a class="nav_item" href="/education/index.php" > &nbsp;RESUME </a>
				|
				<a href="/projects/index.php" class="nav_item" style="color:#fff; text-decoration:none;"> &nbsp;PROJECTS </a>
			</nav>
			<br>

			<div id='content'>

				<!-- -------------------------------------------------------------------------------------  -->
				<div>
					<br/>
					<center>
						<h1> Featured Project </h1>
						<div class = 'l'></div>
					</center>
					<br>
					<center>
						<img src="images/logo_small.png">
					</center>
					<div>
						<FIELDSET>
							<LEGEND>
								<b>Demos</b>
							</LEGEND>
							<div style="position:relative; left:1.5em;">
								<a href="http://campusconquest.com/v1.2/login.php" >Log in</a>
								<br>
								<a href="http://campusconquest.com/version1.1/dashboard.php" >Main Dashboard</a>
								<br>
								<a href="http://campusconquest.com/chat/index.php" >Live Chat Engine Test</a>
								<br>
								<a href="http://campusconquest.com/version1.0/demos/00-TerritoryGrab.php" >Territory Grab</a>
								<br>
								<a href="http://campusconquest.com/version1.0/demos/01-AttackPhase.php" >Attack Phase</a>
							</div>
						</FIELDSET>

						<div >
							Campus Conquest is a project I've been working on since July of 2012. It initially started as a fun
							project to create a Risk game in Java, but rapidly evolved to become something completely different.
							It now stands as more of a capture-the-flag type game with very different gameplay, and is online
							for very easily distributed gameplay with many players. One of the main focuses of the game is use of
							WebGL, a graphics library that allows for powerful 3D graphics in a browser [though not supported in IE].

						</div>
					</div>
				</div>
			</div>
			<div>
				<br />
				<br />

				<div class="proj_border ">
					<div class ="cushion" >
						<div  class="proj_title">
							<a><h2>Gameplay</h2></a>
							<a href="images/mcgraw.png"  rel="lightbox[cc]" title="McGraw Tower rendered in Chrome" date=""> <img src="images/mcgraw_small.png" alt="" align="right" class = "main_img" style="margin:10px;"/></a>
							<h3>A New Turned-Based Strategy Game </h3>
							<div class="desc">

								Campus Conquest is turn-based strategy, capture-the-flag game. Each team places their flag
								in any of the buildings that they start with, and must defend their flag while fighting to capture
								their opponents'. The tenetive rule set includes:
								<ul>
									<li>
										Flags are hidden from enemies
									</li>
									<li>
										Flags are revealed when the territory that has the flag is attacked
									</li>
									<li>
										When a flag moves, it becomes hidden again
									</li>
									<li>
										All teams are notified whenever a flag moves
									</li>
									<li>
										A team's capitol provides significant additional defense, but will reveal the flag
									</li>
								</ul>

								Another significant aspect is unit distribution and determining a winner when buildings are
								attacked. This will be mostly deterministic but with some random probability. Currently
								I'm shooting for a confidence interval (CI) of about 20%, meaning a player can determine the
								outcome of attacking within 20% of the actual.
								<br />
								<br />
								Because of balance issues, are expected to change.
								<br />
								<br />
							</div>
						</div>
					</div>
				</div>
				<br>

				<div class="proj_border ">
					<div class ="cushion" >
						<div class="body">

							<a name="maze" ><h2> Features </h2></a>

							<div>
								<a href="images/terrain.png"  rel="lightbox[splat]" title="Terrain" date="Wire mesh overlayed in foreground to show geometry."><img src="images/terrain_small.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>
								<a href="images/particles.png"  rel="lightbox[splat]" title="A Particle System" date=""><img src="" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>

								<div class="desc">
									<ul>
										<li>
											Accounts to save games and keep friend lists
										</li>
										<li>
											Detailed shaders for forests and water to be added
										</li>
										<li>
											Weekly massive multiplayer games organized by college and year
										</li>
										<li>
											Realtime Chat
										</li>
									</ul>
								</div>

							</div>
						</div>
					</div>
				</div>

				<br>

				<div class="proj_border ">
					<div class ="cushion" >
						<div class="body">

							<a name="maze" ><h2> Servers &amp; Backend</h2></a>
							<h3>PHP, SQL, and Websockets </h3>
							<a href="images/pokejouki.png"  rel="lightbox[poke]" title="Poke Jouki" date=""><img src="images/pokejouki.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>

							<div>

								<div class="desc">
									To communicate with many clients at once, the server is based in PHP. The gamestate is in SQL, sent as a JSON object to the client.
									Game updates currently occur through polling the server often. To drastically increase speed and efficiency,
									the server may be switched to a python or ruby framework utilizing websockets to establish a connection for
									push notifications and updates.

									<br>
								</div>

							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="proj_border ">
					<div class ="cushion" >
						<a name="cw" class="proj_title"><h2>Graphics </h2></a>
						<h3>Powered by WebGL</h3>
						<img src="images/html5.png" alt="" align="right"  style="margin:10px;"/></a>
						<img src="images/webgl.png" alt="" align="right"  style="margin:10px;"/></a>

						<div class="desc">
							HTML 5's WebGL is a new technology that allows for powerful 3D graphics that run in a browser.
							WebGL and along with HTML's 2D drawing element, Canvas, form the core of Campus Conquest's graphics.
							<br>
							<br>
							<h3>HTML 5's Canvas Element</h3>
							In addition to WebGL, the game uses Canvas for 2D elements. These include items such as the wheel displaying each teams number of
							units as well as current turn and other stats and controls. In the main display, a Canvas element is
							overlayed on top of the WebGL render window to display building names and stats.

							<br>
							<br>
							<br>
							<br>
							<br>
						</div>
					</div>
				</div>
			</div>
			<br>

			<div class="proj_border ">
				<div class ="cushion" >
					<div class="body">

						<a name="maze" ><h2> Flexibility</h2></a>
						<a href="images/map_creator.png"  rel="lightbox[cc]" title="The Map Builder" date=""><img src="images/map_creator_small.png" alt="" align="right" class = "main_img" style="max-width: 35%;" /></a>

						<div>

							<div class="desc">
								<br>
								<h3> The Core Design </h3>
								Campus Conquest is set on the Cornell campus now, but isn't limited
								to where a game could take place. When creating the game, key design
								decisions were made to decouple the map and ruleset as far apart as
								possible, meaning a map can be edited to fix building balance issues
								and new maps can be added to the game, all without having to touch the
								internal mechanics and code at all.
								<br>
								<br>
								<h3> The Map Creator </h3>
								One of the primary concerns with the initial concept of Campus Conquest was balance issues.
								What happens if the inherent layout of the campus forced one or two buildings to
								be key to the entire game? Given capitols for each college, will there be clear
								advantages to say, engineers for having Duffield Hall over hotel admin students starting
								with the Statler? To aid in later resolving balance issues as well as give others a say,
								the map creator was built.
								<br>
								<br>
								The map creator is a simple program that loads in a series of object files, places them into
								the scene, and using those models, allows a user to generate a JSON map for use in the game.
								This is powerful because fixing maps is a sinch instead of having to manually edit a JSON file.
								<br>
								<br>
								<h3> To the Ivy League and Beyond </h3>
								Using the map creator, one can load a folder of different models. The program allows for
								easily creating links between territories and exporting a JSON object file for
								use in the game. This allows maps to be rapidly added later for any school.
							</div>

						</div>
					</div>
				</div>
			</div>
			<br>

		</div>
		</div>
		<br>

		</div>

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

