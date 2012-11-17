<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>


		<title>Bryce Evans | <?php echo $title; ?> </title>
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<LINK REL=StyleSheet HREF="../css/global.css" TYPE="text/css">
			<LINK REL=StyleSheet HREF="../css/slider.css" TYPE="text/css">

		<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>

	</head>

	<body>

		<div id="page">
			<a href="../index.php">back</a>

			<div id=header>
				<div id='bar'>
					<ul>
						<li type='none'>
							<img id="about" src="../images/proj_small.png" ALIGN= LEFT >	<h5>bryce evans</h5>
						</li>
					</ul>

				</div>

				<div id='header'>
					<div id='curPage'>
						<span><h1><b>&nbsp<?php echo $title; ?></b></h1></span>
					</div>

				</div>
				<br />
			</div>
			<div id='content'>
				<br />
			
			

				<h1> <?php  echo $contentTitle; ?> </h1>
				<br />
				<?php  echo $content; ?>

			</div>
			<div id="footer">
				<p>
					&copy; 2012 Bryce Evans
				</p>
			</div>
		</div>
	</body>
</html>
