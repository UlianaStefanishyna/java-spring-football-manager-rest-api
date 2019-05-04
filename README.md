# java-spring-football-manager-rest-api
Design and implement a REST API that allows to manage football teams and players.Each ​team​ consists of multiple players. A ​player​ can be assigned to one and only one ​team​,but can exists without a team. Data required for each player consists of ​first name​, ​lastname​, ​position​ and ​birthday​. A team should also have a ​name​ which is mandatory. Apartfrom that, team should have a capitan. Capitan is one of the players assigned to that team.REST API should provide CRUD endpoints for both ​team​ and ​player. ​It should provide a listof additional endpoints for each of the following operations:-get a list of all teams-get a list of all players-get a list of players by team-get a capitan by team-add a new(not existing) player to a team-assign a capitanTech. notes:API should follow REST conventions. Application should be implemented using ​Spring MVCbacked by ​Spring Data JPA​, ​Hibernate​ and ​PostgreSQL​ database. Your solution should alsoprovide an ​SQL​ script that initializes database tables.