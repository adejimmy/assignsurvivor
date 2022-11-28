# assignsurvivor
Java 11 was used with Intellij

MySql seeting is on application.yml file

Rest API was used

    To Insert a Survivor a POST request is used, url is found below http://localhost:8080/survivors the request body is shown below { "name" : "6851", "age" : "11", "gender" : "meri", "latitude" : "11", "lonitude" : "11", "resources" :[{ "resourcesname" : "Water" }, { "resourcesname" : "food" }, { "resourcesname" : "medication"}]}

2)To update the logitude/Latitude its a put request http://localhost:8080/survivors/id the id on the url above is the idsurvivor in the survivor table it is unique but it is not the primary key { "latitude" : "110878787", "lonitude" : "11" }

3)To report a survivor as contaminated its a put request http://localhost:8080/survivors/report/id the id on the url above is the idsurvivor in the survivor table it is unique but it is not the primary key the request body is shown below { "message" : "Contaminated" } I added a request body because i wanted to insert this report to a table but because i started the task yestaday i didnt add it, but everything works very fine

4)To get the percentage of survivors infected http://localhost:8080/survivors/infected No request body its a get

5)4)To get the percentage of survivors uninfected http://localhost:8080/survivors/notinfected No request body its a get

6)To get all infected survivors, its a get request no body http://localhost:8080/survivors/allinfected

6)To get all uninfected survivors, its a get request no body http://localhost:8080/survivors/allnotinfected
