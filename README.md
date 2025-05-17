# data-archival-service

Creating a monorepo to deploy the application on AWS with documentation and design thinking notes. 
This monorepo contains application for data-archival-service. 

This is a basic fullstack setup deployed on AWS (Production ready)

------- DO NOT CHANGE ABOVE -------------------

------- Dynamic Structure Below ---------------

!!Required structure and notes needs to be added here from below:

## [Architecture Diagram]
<img src="docs/da_brainstorming.jpg" alt="Architecture Diagram" width="500"/>



# Scope of work:

- Data mobility: move inactive data from RDS to archive db/ s3
- Configuration flexibility: archival rules (easy to change)
- Table specific rules
- REST apis: secure endpoints to view manage data 
- Authentication and Authorization: role based access control(admin)
- Scheduled archival/ cron jobs
- Logs/monitoring/alerts/notifications
- Platform compatibility: docker based  deployable on any linux machine 

# Requirement gathering:

## Functional requirements:
- Archival logic
- Configuration system to configure retention period
- Apis
- cron job

## Non-funtional requirements:
- security
- portability
- performance
- scalability
- monitoring 
- Cloud Deployabale (multiple environment support)

# Structure: (proposed tech stack) 
- backend - java 17, spring boot 3+ 
- scheduler - spring has annotation @scheduled 
- Database (RDS) - PostGresSQL
- Auth - Spring security
- Archieve - AWS s3(deep archieve optional), PostGresSQL
- Devops - docker + github actions 
- UI - React + MUI 


