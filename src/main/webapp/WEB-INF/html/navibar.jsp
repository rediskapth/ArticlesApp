<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

           <nav class="navbar navbar-inverse">
              <div class="container">
                <div class="navbar-header">
                  <a class="navbar-brand" href="/">Articles Application</a>
                </div>
                <ul class="nav navbar-nav">
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Articles <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/articles/findArticles">Find All Articles</a></li>
                      <li><a href="/articles/form/create">Create New Article</a></li>
                      <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="/articles/statistic">Show Articles Statistic</a></li>
                      </security:authorize>
                    </ul>
                  </li>
                  <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="/users/findUsers">Find All Users</a></li>
                        <li><a href="/users/form/create">Create New User</a></li>
                      </ul>
                    </li>
                  </security:authorize>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a style="float: right" href="/logout">Logout</a>
                    </li>
                </ul>
              </div>
           </nav>
