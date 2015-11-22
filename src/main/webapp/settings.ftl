<#include "main-template.ftl"/>
<#macro m_body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-offset-2" id="registration-container">
            <div class="col-lg-5 col-lg-offset-4">
                <form action="/saveImg" method="post" enctype="multipart/form-data">
                    <img src="/avatar.jpg?username=${user.login}"
                         class="img-responsive" alt="" style="margin: 0 auto;">
                    <input type="file" name="photo" style="margin-top: 10px;" class="form-control" accept="image/*,image/jpeg" required>
                    <button type="submit" class="btn btn-block btn-primary">Refresh</button>
                </form>
                <br>
                <br>
                <form action="/settings" method="post">
                    <label for="name">You decided to change a name?</label>
                    <input type="text" name="firstName" class="form-control" value="${user.firstName}" required>

                    <label for="lastname">Or surname?</label>
                    <input type="text" name="lastName" class="form-control" value="${user.lastName}" required>

                    <button type="submit" id="button-reg" class="btn btn-block btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>
</#macro>
<@main title="Registration"/>