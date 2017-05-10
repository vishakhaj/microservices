#! /usr/bin/env bash
sudo consul kv put config/sampleservice/server.port 8888
sudo consul kv put config/sampleservice/users http://localhost:8888/mockdata/user.json
