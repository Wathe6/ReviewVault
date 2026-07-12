Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

$RootDir = Resolve-Path "$PSScriptRoot\.."
$ComposeFile = "infrastructure/docker-compose.services.yaml"

Push-Location $RootDir

try {

    .\gradlew.bat clean

    Write-Host "--------------------------------------------"
    Write-Host "Building Eureka..."
    Write-Host "--------------------------------------------"

    .\gradlew.bat :services:eureka:bootJar

    Write-Host "--------------------------------------------"
    Write-Host "Building Gateway..."
    Write-Host "--------------------------------------------"

    .\gradlew.bat :services:gateway:bootJar

    Write-Host "--------------------------------------------"
    Write-Host "Building Media..."
    Write-Host "--------------------------------------------"

    .\gradlew.bat :services:media:bootJar

    Write-Host "--------------------------------------------"
    Write-Host "All projects built successfully."
    Write-Host "Artifacts:"
    Write-Host " - eureka:  services/eureka/build/libs/"
    Write-Host " - gateway: services/gateway/build/libs/"
    Write-Host " - media:   services/media/build/libs/"
    Write-Host "--------------------------------------------"

    Write-Host "--------------------------------------------"
    Write-Host "Starting Docker Compose..."
    Write-Host "Press Ctrl + C to stop and run docker compose down."
    Write-Host "--------------------------------------------"

    docker compose -f $ComposeFile up --build
}
finally {
    Write-Host ""
    Write-Host "--------------------------------------------"
    Write-Host "Stopping Docker Compose..."
    Write-Host "--------------------------------------------"

    docker compose -f $ComposeFile down

    Pop-Location
}