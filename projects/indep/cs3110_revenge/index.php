<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<title>Bryce Evans | Projects </title>
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<LINK REL=StyleSheet HREF="../../../css/global.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="../../../css/slider.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="../../../css/cards.css" TYPE="text/css">
		<link rel="stylesheet" href="../../../css/lightbox.css" type="text/css" media="screen" />
<style>.code{
	font-size:15px;
	font-family:consolas, monospace
}</style>
		<script type="text/javascript" src="../../../js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="../../../js/lightbox.js"></script>

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
		<div id="header">

		</div>
		<div id='page'>

			<span id = 'name'><img id = "secimg" src="../../../images/proj_small.png" ALIGN = LEFT> bryce evans </span>
			<nav>
				<a class="nav_item" href="/index.php"> &nbsp;Home </a> |<a class="nav_item" href="/about/index.php"> &nbsp;ABOUT </a> | <a class="nav_item" href="/education/index.php"> &nbsp;RESUME </a> | <a href="/projects/index.php" class="nav_item" style="color:#fff; text-decoration:none;"> &nbsp;PROJECTS </a>
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
						<h2> CS 3110 Revenge</h2><span style="font-size:22px; color:#ccc;">( </span><span class="code" style="font-size: 22px;"><span style="color: #f9263c;">fun</span> <i style="color: #fd971f;">is a keyword</i></span><span class= "code" style="font-size:26px; color:#ddd;"> -&gt; _ </span><span style="font-size:22px; color:#ccc;"> )</span>
						<div class = 'l'></div>
					</center>
					<br>
					<div>
						<br />
						<div >
							This code snippet was written after taking CS 3110, where the test questions were notoriously obfuscated, frequently 
							referencing other aspects of the class and other jokes from the TAs (such as the "yolo" function below). This is a compilation of many of those questions, with an addition
							of several more. 
							
							In here are examples of such things as:
							<ul>
								<li>Infinite Streams and functions on them such as interweaving them functionally</li>
								<li>Folds</li>
								<li>Multiple implementations of Pervasives, such as <span class="code">fold_left</span> being tail recursive, non-tail recusive, and with references</li>
								<li>Recursive functions without the use of <span class="code">rec</span></li>
								<li>Advanced Continuation Passing Style</li>
							</ul>
							<center>
							The code below returns "A+"
							</center>
						</div>
					</div>
				</div>
				<div>
					<br />

					<br />
					<pre class="code">
(* A personal challenge to the 3110 TAs *)

type 'a hw = ProblemSet of 'a * (unit -> 'a hw)

let cs thirtyone ten revenge = 

	let course_staff = ref thirtyone in
	let grades = ten in

 	let ps1 = List.nth grades 0 in
	let ps2 = List.nth grades 1 in
	let ps3 = List.nth grades 2 in
	let ps4 = List.nth grades 4 in
	let ps5 = List.nth grades 5 in
	let ps6 = List.nth grades 7 in 

	let thirtyone = List.length thirtyone in
	let ten = List.length ten in

	let did_I_pass = Random.bool() in

	let veryrare = ref true in

	let sung = max thirtyone ten in

	let string_of_int (num : int) : string =
		(Char.escaped (char_of_int num)) in

(* The point at the end of this arrow marks the end of the 80 character limit ->_ *)

	let hashtag pro = 
		let ben = !pro in
		pro := not !pro;
		ben in

	(hashtag veryrare);

	let grade expected_grade = 
		expected_grade / 2 in

	let piazza answer = () in

	let rec substitution f acc lst =
		match lst with 
		| [] -> acc 
		| h::t -> substitution f (f acc h) t in

	let induction f acc lst =
		let fl = ref revenge in
		fl := (fun f acc l -> 
			match l with 
			| [] -> acc
			| [x] -> f acc x
			| h::t -> (!fl) f (f acc h) t
		);
		(!fl) f acc lst in

	let rec alpha_sub f lst acc =
		match List.rev(lst) with 
		| h::t -> alpha_sub f (List.rev(t)) (f h acc)
		| [] -> acc in


	let beta_sub f xs acc =
		let g todd pablo = fun v -> todd (f pablo v) in
		(substitution g (fun jane -> jane) xs) acc in
		
	let know thy lambda calc = 	 	
 		if did_I_pass
 			then alpha_sub thy lambda calc
			else beta_sub thy lambda calc in 

	(* debugging... WHY NO DEBUGGER? *)
	let beat_the_mean _ =
		let mean = know (fun el acc -> 
			acc ^ (string_of_int el)
		) grades "" in 
		print_endline mean 
	in

	let lo = grades in
	let yolo lo yo = 
		let yo lo = lo yo 
		and lo yo = yo lo in  yo (lo yo) in

	piazza(beat_the_mean());

	let rec ben1 math () = 
		let dylan = match math with
		| [] -> []
		| [jane] -> [jane]
		| andy::mike -> mike in
		ProblemSet (dylan,ben1 dylan) 

	and ben2 lst () = 
		let ret2 = match (List.rev lst) with
		| [] -> []
		| [jane] -> [jane]
		| chris::andrew -> List.rev andrew in
		ProblemSet (ret2,ben2 ret2) in

	let rec which_is_which yo lo yolo loyo =
		match (yo yolo ()) with
	  | ProblemSet (a, b) -> ProblemSet (a, fun () -> 
	  	 which_is_which lo yo a loyo )  in


	let rec tro g dor = 
		let ProblemSet (v,f) = dor in
		match g with
		| [] -> None
		| [chris] -> Some chris
		| ranjay::jeremy -> tro v (f()) in


	let yo = which_is_which ben1 ben2 in

	let lol = (yolo yo lo) in

	let sleep =  tro lo lol in

	let zardoz = 
		(match sleep with
		| Some zzzs -> grade zzzs
		| None -> failwith "Another all-nighter?") in

	let get_new_cube lst not_in_the_collection =
		List.fold_left (fun harris cube -> 
			if not (cube=not_in_the_collection) 
			then cube::harris else harris) [] lst
	in 

	let jianneng = get_new_cube (!course_staff)  in

	let wen hai = 
		if hashtag veryrare then
			course_staff := hai "Greg";
		 
		if (max sung (min thirtyone ten)) &lt; 42 then
			course_staff := (hai "Sam");
	in wen jianneng;

	let office_hours help_lst = 
		let count = induction (fun acc el-&gt;

			piazza(substitution (fun acc2 el2 -&gt;
				if fst el2 = el then 
				begin
				(snd el2) := (!(snd el2)) + 1;
				true
				end	else acc2

			) false acc);

		(el,ref 1) :: acc

		) [] (!help_lst) in

		let max = substitution (fun acc el -> 
			let (n,c)=el in 
			let (nacc,cacc)=acc in 
			if !cacc > !c then acc else el
		) ("",ref 0) count in


		fst max in

	let ramin = Char.escaped((office_hours (course_staff)).[zardoz-42]) in

	let zaRDoZ = ramin ^ string_of_int (zardoz + 1) in
	
(* * * * * * * * * * * *)  zaRDoZ  (* * * * * * * * * * * *)


let final = 
    let thirtyone = ["Greg";"Sam";"Michael";"Chris";"Ben";"Ben";
      "Andrew";"Andrew";"Andrew";"Jane";"Jeremy";"Jianneng";
      "Ranjay";"Pablo"; "Dylan";"Harris";"Todd"] in
	
    let ten = [63; 33; 82; 69; 84; 65; 69; 72; 67] in

    cs thirtyone ten (fun is a keyword -> a )
</pre>
					<br>

					
					<br>

					<br>
					<br>
					<br>
					<br>
					<br>
				</div>
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
				&copy; 2013 Bryce Evans
			</p>
		</div>
		</div>

	</body>
</html>

</div>
</div>
</body>
</html>


