Answers:

1. I used AngularJS/CSS/HTML technology stack in front-end implementation.

   AngularJS is the most 'complete' java script framework supporting MVC design pattern on the client side.
   It offers many out of the box functionalities streamlining application development. In spite of being pretty 'fresh' technology (1.0 version released in march 2012), angular gained recognition among web developers community.

   As back-end technology I used Spring MVC
	
   It has very good support for REST web services and JSON-based communication. By means of those features Spring MVC integrates very well with angularJs client.
   Appart from that, spring offers powerful, easy to use Dependency Injection system. 

2. I designed the application so that it allows to plug any persistance layer in (at least in theory).
   It should be enough to use some relational database + ORM mapper in front of it (e.g. hibernate, since spring integrates very well with it).
   From the application point of view, proper DAO interfaces would have to be implemented replacing 'Memory DAOs' being used currently.

3. I spent on the project 6 full days. Around 8-9 hours per day

4. Given that new commodities on stock are published at long intervals (e.g. each night), I would set up scheduled batch job running every night,
   pulling out data from warehouse and populating OnlineStore's database. Application should ensure that when job finished, its internal caches are either expired or updated.
   In other scenario, when commodities are published randomly many times during the day, I would insist to set up real time feed mechanism based on messaging.
