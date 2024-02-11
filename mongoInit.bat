echo Initializing MongoDB && mongosh && use admin && db.createUser({ user: "user" , pwd: "password", roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase", "readWriteAnyDatabase"]})

