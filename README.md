# Task-Tracker-App

The app is designed to work with the [Task-Tracker-Device](https://github.com/Task-Tracker-Systems/Task-Tracker-Device).

## Build

### Local
Run the gradle build in each directory or use the `build.sh` file.

### Docker
Powershell
```Powershell
docker build -t task-tracker-systems/task-tracker-app:1 .
docker run --rm -v ${PWD}:/dev/mnt task-tracker-systems/task-tracker-app:1
```

### Github
Latest build is available through [Github Actions](https://github.com/Task-Tracker-Systems/Task-Tracker-App/actions)