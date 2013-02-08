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