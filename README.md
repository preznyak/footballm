# Football Manager by lpreznya

## What will we do? 
Build a RESTful API for football manager basic operations, like:
- List all football player from the DB
- Create new player
- Search players by different attributes
- Manage Football clubs
- Transfer market operations
- etc

### To run the application, please follow the next steps:
1. Start a mysql docker container with the following command:
   docker run --detach --name footballm-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=<your_password> -e MYSQL_DATABASE=football_manager -e MYSQL_USER=manager -e MYSQL_PASSWORD=<your_password> -d mysql
2. Update the application.properties file to match your setting. 
3. Run the Project 

All the information are gathered from the [transfermarkt](https://www.transfermarkt.com/) site.