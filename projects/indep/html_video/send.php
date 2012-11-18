<?php

$to = "bae43@cornell.edu";
$from = "BAE43";
$subject = "PHP E-Mail Test";
$text = "IT WORKS";

$image = $_GET["imgform"];

$message = "<html><head>"+$text+"</head><body>";
$message .= base64_encode_image($image, 'png');

$headers = "From: $from";
$headers .= "Content-type: text/html";

mail($to, $subject, $message);
	include '../../template.php';
	
?>