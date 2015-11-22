<#include "main-template.ftl"/>
<#macro m_body>
<div class="container">
    <div class="col-md-8 col-md-offset-2 container-page container-page" style="background-color:#ffffff;padding:40px;">
        <h2>All users: </h2>
        <input type="text" class="form-control" id="search-input" style="margin-top:20px;" placeholder="Search..">

        <div id="all-users-list">

        </div>
    </div>
    <!--/stories-->
    <div style="clear: both;"></div>
</div>
</div>
</div>
</div>
<script src="/js/search-users.js"></script>
</#macro>

<@main title="All users"/>
