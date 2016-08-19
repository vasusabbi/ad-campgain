# ad-campgain
Create Ad Campaign via HTTP POST &amp; Fetch Ad Campaign for a Partner

build instructions:
-------------------
1. download the source code from github
2. execute "mvn clean install" to build the project at command prompt
3. execute "mvn eclipse:eclipse" incase if you want to create this project in to eclipse 
4. a war file "AdCampaigns\target\AdCampaigns.war" will be created in target folder once the build is done at step 3. [checked in war file as well for testing]

deploy instructions:
--------------------
1. login to the tomcat web application manager
2. to to the "Deploy->WAR file to deploy" section
3. click on the choose the file buttion to select the war file
4. clieck on deploy
5. /AdCampaigns application path will be listed in the applications list with running status true

testing the service:
--------------------

below are the uris to fetch the partner ads :
---------------------------------------------
http://localhost:8080/AdCampaigns/rest/adcampaignsService/ad
http://localhost:8080/AdCampaigns/rest/adcampaignsService/ad/10001

wadl link to create a project in SOAPUI:
----------------------------------------
http://localhost:8080/AdCampaigns/rest/application.wadl

integration test file:
----------------------
src\test\java\com\adcampaigns\rest\AdCampaignsServiceTest.java

** post the date to create an ad either through test java file by executing it / post it using the SOAPUI
** provided uris above to get the ad details based on partner_id / complete list of active ads
