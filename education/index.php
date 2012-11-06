<?php 

$title = "Education";
$contentTitle = "Coursework";
$content = <<<EXCERPT

			<div class='slider_section_title_f' id="f12_title">
				<div class = 'l1'></div>
				Fall 2012
				<div class = 'l1'></div>

			</div>

			<div class='slider_section'  id='f12'>
				<table>

					<tr>
						<td>CS 4620: Computer Graphics I</td><td>3</td><td>N/A</td>

					</tr>
					<tr>
						<td>CS 4621: Computer Graphics Practicum</td><td>2</td><td>N/A</td>

					</tr>
					<tr>
						<td><a href="http://www.engineering.cornell.edu/admissions/undergraduate/apply/transfer/course_descriptions.cfm#CP_JUMP_8541"target="_blank">CS 3110: Functional Programming</a></td><td>4</td><td>N/A</td>

					</tr>
					<tr>
						<td><a href="http://www.engineering.cornell.edu/admissions/undergraduate/apply/transfer/course_descriptions.cfm#CP_JUMP_8540"target="_blank">CS 2800: Discrete Structures</a></td><td>3</td><td>N/A</td>

					</tr>
					<tr>
						<td><a href="http://registrar.sas.cornell.edu/cosarchive/Courses10/CoSdetail.php?college=AS&number=2940&prefix=MATH&title=Linear+Algebra+for+Engineers+(MQR)"target="_blank">MATH 2940: Linear Algebra</a></td><td>4</td><td>N/A</td>

					</tr>
				</table>
			</div>

			<div class='slider_section_title_s' id="s12_title">
				<div class = 'l1'></div>
				Spring 2012

				<div class = 'l1'></div>
			</div>
			<div  class='slider_section' id='s12'>
				<table>

					<tr>
						<td><a href="http://www.cs.cornell.edu/courses/cs2112/2012sp/"target="_blank"><span id="link1">CS 2112: Data Structures </span> <span id="link2">(Honors)</span> </a></td><td>4</td><td>A-</td>

					</tr>
					<tr>
						<td><a href="http://www.cs.cornell.edu/courses/cs1114/2012sp/"target="_blank">CS 1114: MatLab with Robotics (Honors)</a></td><td>4</td><td>N/A</td>

					</tr>
					<tr>
						<td><a href="http://www.engineering.cornell.edu/admissions/undergraduate/apply/transfer/course_descriptions.cfm#CP_JUMP_8563"target="_blank">MATH 1920: Multivariable Calculus</a></td><td>4</td><td>N/A</td>

					</tr>
					<tr>
						<td><a href="http://www.engineering.cornell.edu/admissions/undergraduate/apply/transfer/course_descriptions.cfm#CP_JUMP_8531"target="_blank">CHEM 2090: Engineering Chemistry</a></td><td>4</td><td>N/A</td>

					</tr>
					<tr>
						<td><a href="http://www.arts.cornell.edu/knight_institute/fws/FWS%20Brochures/2012SP_Brochure.pdf"target="_blank"> ENGL 1158.102: Writing Seminar </a></td><td>3</td><td>N/A</td>

					</tr>

				</table>
			</div>

			<div class='slider_section_title_f' id="f11_title">
				<div class = 'l1'></div>
				Fall 2011

			</div><div class = 'l1'></div>
			<div   class='slider_section'  id='f11'>

				<a href="http://www.arts.cornell.edu/knight_institute/fws/FWS%20Brochures/2012SP_Brochure.pdf"target="_blank"> ECE 1210: Modern Computing Devices</a>
				<div class = 'l2'></div>

				<a href="http://courses.cornell.edu/preview_course.php?catoid=12&coid=95469&print"target="_blank">PHYS 1112: Mechanics & Heat</a>
				<div class = 'l2'></div>

				<a href="http://courses.cornell.edu/preview_course_nopop.php?catoid=14&coid=163231"target="_blank">MATH 1910: Engineering Calculus</a>
				<div class = 'l2'></div>

				<a href="http://courses.cornell.edu/preview_course_nopop.php?catoid=14&coid=163231"target="_blank">ENGL 1111.102: Writing Seminar</a>
				<div class = 'l2'></div>
				</table>
				<script type="text/javascript">
                    (function() {
                        //color hovered links
                        $(document).ready(function() {
                            $('#red-blue-link').hover(function() {
                                $(this).children('span').toggleClass('invert')
                            })
                            //highlight current selected semester
                            $('.clicky').click(function() {
                                $('.clicky.selected').removeClass('selected');
                                $(this).addClass('selected');
                            })
                            //show current page and hide others
                            var curpage = '#f12';

                            $('#s12').hide();
                            $('#f11').hide();

                            $('#f12_title').click(function() {
                                if($('#f12').is(":hidden")) {
                                    $(curpage).slideUp();
                                    $('#f12').slideDown();
                                    curpage = '#f12';
                                }
                            });

                            $('#s12_title').click(function() {
                                if($('#s12').is(":hidden")) {
                                    $(curpage).slideUp();
                                    $('#s12').slideDown();
                                    curpage = '#s12';
                                }
                            });

                            $('#f11_title').click(function() {
                                if($('#f11').is(":hidden")) {
                                    $(curpage).slideUp();
                                    $('#f11').slideDown();
                                    curpage = '#f11';
                                }
                            });
                        });

                    })();

				</script>


EXCERPT;

include '../template.php';


?>

