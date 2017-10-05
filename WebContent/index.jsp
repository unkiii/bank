<%@page import="java.util.List"%>
<%@ page import="controlador.*" %>
<%@ page import="model.*" %>
<%@ page import="servlet.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>


    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">

    <title>Bank | Inicio</title>
    
    <script type="text/javascript">
		$(document).ready(function() {
    		setTimeout(function() {
        	$(".content").fadeOut(1500);
    		},1500);
		});
	</script>
	

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="css/plugins/toastr/toastr.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body>
<!-- Permetre acces nomes si hi ha una sessio -->
<%
	HttpSession sesion=request.getSession(); 
	String nif=null;
	String usuNif=null;
	int permis=0;
	try{
		if(sesion.getAttribute("nif") == null){	
			session.invalidate();
			response.sendRedirect("login.jsp");
		}

		usuNif = sesion.getAttribute("nif").toString();
		permis = Integer.parseInt(sesion.getAttribute("permis").toString());
		
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();			
			}
		}
	}catch(Exception e){}
	finally{}
		
%>





<script type="text/javascript">
	function submitform(){
  		document.logoutForm.submit();
	}
</script>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element"> <span>
                            Benvingut Sr.
                            <br> 
                            
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <%
                            	UsuariDAO udao = new UsuariDAO();
                            	String usuNom = udao.nomClient(usuNif);
                            %>
                            <span class="clear"> <span class="block m-t-xs">Nom: <strong class="font-bold"> <%=usuNom %></strong>
                            <span class="clear"> <span class="block m-t-xs">DNI: <strong class="font-bold"> <%=usuNif%></strong>
                            
                            <!-- 
                             </span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span> </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="profile.html">Profile</a></li>
                                <li><a href="contacts.html">Contacts</a></li>
                                <li><a href="mailbox.html">Mailbox</a></li>
                                <li class="divider"></li>
                                <li><a href="login.html">Logout</a></li>
                            </ul>
                             -->
                        </div>
                      
					<li>
                        <a href="index.jsp"><i class="fa fa-bank"></i> <span class="nav-label">Inici</span></a>
                    </li>
                    
                    <li>
                        <a href="index.jsp"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Estat dels comptes</span></a>
                    </li>
                    
 					<li>
                        <a href="index.jsp"><i class="fa fa-sort-desc"></i> <span class="nav-label">Ingress</span></a>
                    </li>
                    
                    <li>
                        <a href="index.jsp"><i class="fa fa-sort-up"></i> <span class="nav-label">Extracte</span></a>
                    </li>
					
					<li>
                        <a href="index.jsp"><i class="fa fa-exchange"></i> <span class="nav-label">Transferencia</span></a>
                    </li>
                    
                    <li>
                        <a href="pagaments.jsp"><i class="fa fa-money"></i> <span class="nav-label">Pagaments</span></a>
                    </li>
                    
                    <li>
                        <a href="index.jsp"><i class="fa fa-calendar"></i> <span class="nav-label">Auto</span></a>
                    </li>
                </ul>
            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
            <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
               
            </form>
        </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                <%
                	registremovimentsDAO rDAO = new registremovimentsDAO();
                String lastCon = "";
                lastCon = rDAO.ultimlogin(usuNif);
                %>
                    <span class="m-r-sm text-muted welcome-message">Ultima connexio:<b> <%=lastCon %> </b></span>
                </li>
               <li>
               
                    <span class="logout-spn" >                
						<form method="post" action="Logout" name="logoutForm">
							<a href="javascript: submitform()"><b>LOGOUT</b><br></a>
						</form>
					</span>
                </li>
            </ul>

        </nav>
        </div>
                <div class="row  border-bottom white-bg dashboard-header">
                
                <!-- Comprova quins comptes te i els mostra -->
                
                	<!-- Si el disposa el mostra -->
                    <div class="col-lg-3">
                        <div class="ibox float-e-margins">
                           
                            
                            <% 
                            comptesDAO cdao = new comptesDAO();
                            List llista;
                            List type;
                            llista = cdao.infoCompte(usuNif);
                            String saldo = llista.get(0).toString();
                            String tipus = llista.get(1).toString();
                            type = cdao.tipusCompte(tipus);
                            %>
                            
                             <div class="ibox-title">
                                <h5><%=type%></h5>
                            </div>
                            
                            <div class="ibox-content">
                                <h1 class="no-margins"> <%=saldo%> Euros</h1>
                                <small>saldo amb aquest compte  </small>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    <!-- Moneder del usuari "diners en metalic"-->
                    <div class="col-lg-3">
                        <div class="ibox float-e-margins">
                        
                        <% 
	                        List lmetalic;
	                        lmetalic = cdao.infoMoneder(usuNif);
	                        String metalic = lmetalic.get(0).toString();
	                        String numclient = lmetalic.get(1).toString();
	                    %>
                            
                            <div class="ibox-title">
                                <h5>Metalic</h5>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins"><%=metalic %> Euros</h1>
                                <small>Diners que portes a la cartera.</small>
                            </div>
                        </div>
                    </div>
                    

            </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content">
                        <div class="row">
                        <div class="col-lg-4">
                        
                        <%
                        int numfacturesImpagades = 0;
                        numfacturesImpagades = cdao.nfactImpa(numclient);
                        
                        int sumafactures = cdao.sumafactImpa(numclient);
                        
                        %>
                        <!-- Total de factures sense pagar -->
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h5>Total de factures sense pagar</h5> <span class="label label-primary"><%=numfacturesImpagades%></span>
                                </div>
                                <div class="ibox-content">
                                    <div>
                                        <div class="pull-right text-right">
                                            <span class="bar_dashboard">101,70,100</span>
                                            <br/>
                                            <b><%=sumafactures %> Euros</b>
                                        </div>
                                        <h4>La suma total <br> 
                                        de la deuda es de:
                                            <br/>
                                            
                                        </h4>
                                        </div>
                                    </div>
                                </div>
                                
                                
                            
                        </div>
                            <div class="col-lg-4">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title">
                                        <h5>Ultims moviments</h5>
                                        <div class="ibox-tools">
                                            <span class="label label-warning-light">5 Ultims Moviments</span>
                                           </div>
                                    </div>
                                    <div class="ibox-content">

                                        <div>
                                            <div class="feed-activity-list">

                                                <div class="feed-element">
                                                    <div class="media-body ">
                                                        <strong>Compte Corrent</strong> Extract de 50e <br>
                                                        <small class="text-muted">Today 5:60 pm - 12.06.2014</small>
                                                    </div>
                                                </div>
                                                
                                                <div class="feed-element">
                                                    <div class="media-body ">
                                                        <strong>Compte Estalvis</strong> Ingress de 10e <br>
                                                        <small class="text-muted">Today 5:60 pm - 12.06.2014</small>
                                                    </div>
                                                </div>
                                                
                                                <div class="feed-element">
                                                    <div class="media-body ">
                                                        <strong>Compte Pla Pensions</strong> Generats 1e <br>
                                                        <small class="text-muted">Today 5:60 pm - 12.06.2014</small>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <div class="col-lg-4">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h5>Accions Programades</h5>
                                </div>
                                <div class="ibox-content ibox-heading">
                                    <h3>Moviments AutoProgramats</h3>
                                </div>
                                <div class="ibox-content inspinia-timeline">

                                    <div class="timeline-item">
                                        <div class="row">
                                            <div class="col-xs-3 date">
                                                <i class="fa fa-exchange"></i>
                                                6:00 am <br>
                                                <small class="text-navy">Dia 15</small>
                                            </div>
                                            <div class="col-xs-7 content no-top-border">
                                                <p class="m-b-xs">Tasca 1 <strong>Transferencia</strong> de:</p>

                                                <p> <b>Compte Corrent a Compte Estalvis</b></p>

                                                <p>Quantitat: <b>50 Euros</b></p>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="timeline-item">
                                        <div class="row">
                                            <div class="col-xs-3 date">
                                                <i class="fa fa-exchange"></i>
                                                10:00 pm <br>
                                                <small class="text-navy">Dia 16</small>
                                            </div>
                                            <div class="col-xs-7 content no-top-border">
                                                <p class="m-b-xs">Tasca 2 <strong>Transferencia</strong> de:</p>

                                                <p> <b>Compte Corrent a Compte Pla de Pensions</b></p>

                                                <p>Quantitat: <b>50 Euros</b></p>
                                            </div>
                                        </div>
                                    </div>
                                    

                                </div>
                            </div>
                        </div>

                        </div>
                </div>
                
            </div>
        </div>

        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Flot -->
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/demo/peity-demo.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/inspinia.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>

    <!-- jQuery UI -->
    <script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- GITTER -->
    <script src="js/plugins/gritter/jquery.gritter.min.js"></script>

    <!-- Sparkline -->
    <script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

    <!-- Sparkline demo data  -->
    <script src="js/demo/sparkline-demo.js"></script>

    <!-- ChartJS-->
    <script src="js/plugins/chartJs/Chart.min.js"></script>

    <!-- Toastr -->
    <script src="js/plugins/toastr/toastr.min.js"></script>


    <script>
        $(document).ready(function() {
            setTimeout(function() {
                toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    timeOut: 2000
                };
                toastr.success('Loguejat correctament.', 'Benvingut a Bank');

            }, 1300);


            var data1 = [
                [0,4],[1,8],[2,5],[3,10],[4,4],[5,16],[6,5],[7,11],[8,6],[9,11],[10,30],[11,10],[12,13],[13,4],[14,3],[15,3],[16,6]
            ];
            var data2 = [
                [0,1],[1,0],[2,2],[3,0],[4,1],[5,3],[6,1],[7,5],[8,2],[9,3],[10,2],[11,1],[12,0],[13,2],[14,8],[15,0],[16,0]
            ];
            $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
                data1, data2
            ],
                    {
                        series: {
                            lines: {
                                show: false,
                                fill: true
                            },
                            splines: {
                                show: true,
                                tension: 0.4,
                                lineWidth: 1,
                                fill: 0.4
                            },
                            points: {
                                radius: 0,
                                show: true
                            },
                            shadowSize: 2
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: "#d5d5d5",
                            borderWidth: 1,
                            color: '#d5d5d5'
                        },
                        colors: ["#1ab394", "#464f88"],
                        xaxis:{
                        },
                        yaxis: {
                            ticks: 4
                        },
                        tooltip: false
                    }
            );

            var doughnutData = [
                {
                    value: 300,
                    color: "#a3e1d4",
                    highlight: "#1ab394",
                    label: "App"
                },
                {
                    value: 50,
                    color: "#dedede",
                    highlight: "#1ab394",
                    label: "Software"
                },
                {
                    value: 100,
                    color: "#b5b8cf",
                    highlight: "#1ab394",
                    label: "Laptop"
                }
            ];

            var doughnutOptions = {
                segmentShowStroke: true,
                segmentStrokeColor: "#fff",
                segmentStrokeWidth: 2,
                percentageInnerCutout: 45, // This is 0 for Pie charts
                animationSteps: 100,
                animationEasing: "easeOutBounce",
                animateRotate: true,
                animateScale: false,
            };

            var ctx = document.getElementById("doughnutChart").getContext("2d");
            var DoughnutChart = new Chart(ctx).Doughnut(doughnutData, doughnutOptions);

            var polarData = [
                {
                    value: 300,
                    color: "#a3e1d4",
                    highlight: "#1ab394",
                    label: "App"
                },
                {
                    value: 140,
                    color: "#dedede",
                    highlight: "#1ab394",
                    label: "Software"
                },
                {
                    value: 200,
                    color: "#b5b8cf",
                    highlight: "#1ab394",
                    label: "Laptop"
                }
            ];

            var polarOptions = {
                scaleShowLabelBackdrop: true,
                scaleBackdropColor: "rgba(255,255,255,0.75)",
                scaleBeginAtZero: true,
                scaleBackdropPaddingY: 1,
                scaleBackdropPaddingX: 1,
                scaleShowLine: true,
                segmentShowStroke: true,
                segmentStrokeColor: "#fff",
                segmentStrokeWidth: 2,
                animationSteps: 100,
                animationEasing: "easeOutBounce",
                animateRotate: true,
                animateScale: false,
            };
            var ctx = document.getElementById("polarChart").getContext("2d");
            var Polarchart = new Chart(ctx).PolarArea(polarData, polarOptions);

        });
    </script>
</body>
</html>
