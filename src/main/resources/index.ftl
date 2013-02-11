<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title><@s.text name="frontend.title" /></title>
		<link href='http://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css'>
		<script type="text/javascript" src="<@s.url value="/static/jquery/jquery.min.js" />"></script>
		<script type="text/javascript" src="<@s.url value="/static/jquery/jquery.address.min.js" />"></script>
		<script language="javascript">
    	 $('.dropdown-menu').find('form').click(function (e) {
        	e.stopPropagation();
      		});
		</script>
		<@sb.head compressed="true" />
		<@s.url value="" forceAddSchemeHostAndPort="true" var="ctx" />
		<script type="text/javascript">
			(function(w) {
				<#--
				w.confirm = function(message, fnCallback) {
					bootbox.confirm(message, 'Tidak', 'Ya', function(status) {
						fnCallback && fnCallback(status);
					});
					
					return false;
				}
				-->
			})(window);
			
		</script>
		<style type="text/css">
			button.btn, input.btn { min-width: 100px; }
			.navbar-fixed-top .brand { 
				color: #FFF; 
				float: left; 
				margin-right: 10px; 
				text-shadow: 0 px 0 rgba(100,100,100,.1), 0 0 30px rgba(100,100,100,.125); 
			}
			header { margin-bottom: 18px; border-bottom: 2px solid #777; }
			header h1 { font-size: 150%; margin-bottom: 5px; }
			#login {
				margin: 0 0 0;
			}
			.span4 {
				padding-bottom: 10px;
			}
			.playstore {
				border: 1px solid #AFAFAF;
				-moz-border-radius: 0px 10px 10px 0px;
				-webkit-border-bottom-right-radius: 3px;
				-webkit-border-top-right-radius: 3px;
				-khtml-border-bottom-right-radius: 3px;
				-khtml-border-top-right-radius: 3px;
			}
			.brand {
				background-image: url('/statics/img/mik_logo_white.png');
				background-size: 37px;
				background-repeat: no-repeat;
				padding-left: 40px !important;
			}
			p , body{
				font-family: 'Droid Sans', sans-serif;
				margin: 0 0 18px;
				font-size: 13px;
				line-height: 20px;
			}
			h1, h4, .lead {
				color: #501764;
			}
		</style>
	</head>
	<body>
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="brand" href="<@s.url value="/" />"><span class="logo"></span><@s.text name="frontend.header.text" /></a>
					<div class="nav-collapse">
						<ul class="nav">
							<li><a href='<@s.url value="/" />'><i class="icon-home icon-white"></i> Beranda</a></li>
							<li><a href='<@s.url value="/module/prayers" />'>Doa</a></li>
							<li><a href='<@s.url value="/module/reflections" />'>Renungan</a></li>
							<li><a href='<@s.url value="/module/worship" />'>Lagu Rohani</a></li>
							<li><a href='<@s.url value="/module/stories" />'>Kisah Santo dan Santa</a></li>
							<li><a href='<@s.url value="/module/quote" />'>Catholic Quote</a></li>
							<#if request.session.getAttribute("SPRING_SECURITY_CONTEXT")??>
								<li>
									<a href="<@s.url value="/logout" />">Keluar</a>
								</li>
							</#if>
						</ul>
						<#if !request.session.getAttribute("SPRING_SECURITY_CONTEXT")??>
							<ul class="nav pull-right">
		              			<li class="dropdown" id="dropdown">
		         				<a class="dropdown-toggle" data-toggle="dropdown" href="#menu1">
		           						Login
		            				<b class="caret"></b>
		         				</a>
		             				<div class="dropdown-menu" id="dropdown">
		           
		        	    				<@s.form theme="bootstrap" action="/login" cssClass="form-horizontal">
											<@s.actionmessage theme="bootstrap" />
						 				 	 <fieldset class="textbox" style="padding:10px;">
				               					 <input style="margin-top: 8px" type="text" name="username" placeholder="Username" />
			    	           					 <input style="margin-top: 8px" type="password" name="password" placeholder="Passsword" />
			        	       				     <br/>
			            	   				     <p align="right"> <input class="btn-primary" style="margin-top: 8px" name="commit" type="submit" value="  Log In  " /></p>
												</p>
			           							</@s.form>
		           							</fieldset>
		           							<a href='<@s.url value="/forgot" />'">Forgot your Password?</a> 
										</div>
									</div>
		       					</li>
		                	</ul>
						</#if>
					
					</div>
				</div>
			</div>
		</div>
		
		<div class="container content">
			<div class="container">
      <div class="hero-unit">

 	  <h1>Mutiara Iman</h1>
        <p>Layanan aplikasi online dan mobile yang berisi kumpulan doa doa, renungan, quote, dan lain lain</p>
      </div>

      <div class="row">
      <div class="span4">
	      <h4>Kisah Santo dan Santa  | ${stories.title}</h4>
		  <p>
	       		<#assign minititle=(stories.content!"")>
				<#if minititle?length &lt; 250>
					${minititle} <a href='<@s.url value="/module/stories#${stories.id}" />'>[ Read More . . . ]</a>
				<#else>
					${minititle?substring(0,249)} <a href='<@s.url value="/module/stories#${stories.id}" />'>[ Read More . . . ]</a>
				</#if>
		   </p>
	    </div> 
	    <div class="span4">
	      <h4>Lagu Rohani  | ${worship.title}</h4>
		  <p>
	       		<#assign minititle=(worship.content!"")>
				<#if minititle?length &lt; 250>
					${minititle} <a href='<@s.url value="/module/worship#${worship.id}" />'>[ Read More . . . ]</a>
				<#else>
					${minititle?substring(0,249)} <a href='<@s.url value="/module/worship#${worship.id}" />'>[ Read More . . . ]</a>
				</#if>
		   </p>
	    </div>
        <div class="span4">
          <h4>Renungan | ${renungan.title} </h4>
           <p>
           		<#assign minititle=(renungan.content!"")>
				<#if minititle?length &lt; 250>
					${minititle} <a href='<@s.url value="/module/reflections#${renungan.id}" />'>[ Read More . . . ]</a>
				<#else>
					${minititle?substring(0,249)} <a href='<@s.url value="/module/reflections#${renungan.id}" />'>[ Read More . . . ]</a>
				</#if>
		   </p>
       </div>
       </div>
       <div class="row">
       <div class="span4">
       <hr>
          <h4>Doa | ${doa.title} </h4>
           <p>
           	<#assign minititle=(doa.content!"")>
			<#if minititle?length &lt; 250>
				${minititle} <a href='<@s.url value="/module/prayers#${doa.id}" />'>[ Read More . . . ]</a>
			<#else>
				${minititle?substring(0,249)} <a href='<@s.url value="/module/prayers#${doa.id}" />'>[ Read More . . . ]</a>
			</#if>
           </p>
        </div>
	    <div class="span4">
	    <hr>
          <h4>Quote | ${quote.title} </h4>
           <p>
           	<#assign minititle=(quote.content!"")>
			<#if minititle?length &lt; 250>
				${minititle} <a href='<@s.url value="/module/quote#${quote.id}" />'>[ Read More . . . ]</a>
			<#else>
				${minititle?substring(0,249)} <a href='<@s.url value="/module/quote#${quote.id}" />'>[ Read More . . . ]</a>
			</#if>
           </p>
        </div>
	    <div class="span4">
	    <hr>
	    <h4 style="text-align:center;padding-bottom:10px;">Get Android Application now </h4>
	      	<a href="https://play.google.com/store/apps/details?id=org.mutiaraiman.droid" target="_blank"><img class="playstore" src="http://www.android.com/images/brand/google_play_logo_450.png"></a>
	    </div>
</div> 
</div>
		</div>
		
		<footer class="footer modal-footer">
			<div class="container">
				<@s.text name="frontend.footer.text" />
			</div>
		</footer>
	</body>
</html>