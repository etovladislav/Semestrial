<#include "main-template.ftl"/>
<#macro m_body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-offset-2" id="registration-container">
                <form role="form" method="post" id="form-reg" action="/registration">
                    <div class="col-lg-5 col-lg-offset-1">
                        <h3>Registration</h3>
                    </div>
                    <div class="col-lg-5">

                        <label for="login">Логин</label> <div id="div_username"></div>
                        <input type="text" class="form-control" id="login" name="login"
                               placeholder="Enter login">

                        <label for="exampleInputEmail1">Email</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" name="email"
                               placeholder="Enter email" required>

                        <label for="name">Как вас зовут?</label>
                        <input type="text" class="form-control" id="name" name="firstname" placeholder="Enter your name"
                               required>

                        <label for="surname">Тут фамилия</label>
                        <input type="text" class="form-control" id="surname" name="lastname"
                               placeholder="Enter your surname"
                               required>

                        <label for="password1">Пароль</label>
                        <input type="password" class="form-control" id="password1" placeholder="Password" name="password"
                               required>

                        <label for="password2">Повторите, чтобы не ошибиться</label>
                        <input type="password" class="form-control" id="password2" placeholder="Password" name="password1"
                               required>

                        <button type="submit" id="button-reg" class="btn btn-block btn-primary">Зарегистрироваться</button>

                    </div>
                </form>
        </div>
    </div>
</div>
<script src="/js/validate.js"></script>
</#macro>
<@main title="Registration"/>