Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

$RootDir = Resolve-Path "$PSScriptRoot\.."
$ComposeFile = "infrastructure/docker-compose.services.yaml"
$ComposeStarted = $false

function Invoke-GradleTask {
    param([string]$TaskName)

    & .\gradlew.bat $TaskName
    if ($LASTEXITCODE -ne 0) {
        throw "Gradle task '$TaskName' failed with exit code $LASTEXITCODE. Docker Compose was not started."
    }
}

Push-Location $RootDir

try {

    Invoke-GradleTask "clean"

    Write-Host "--------------------------------------------"
    Write-Host "Building Contracts..."
    Write-Host "--------------------------------------------"

    Invoke-GradleTask ":services:contracts:jar"

    Write-Host "--------------------------------------------"
    Write-Host "Building Eureka..."
    Write-Host "--------------------------------------------"

    Invoke-GradleTask ":services:eureka:bootJar"

    Write-Host "--------------------------------------------"
    Write-Host "Building Gateway..."
    Write-Host "--------------------------------------------"

    Invoke-GradleTask ":services:gateway:bootJar"

    Write-Host "--------------------------------------------"
    Write-Host "Building Media..."
    Write-Host "--------------------------------------------"

    Invoke-GradleTask ":services:media:bootJar"

    Write-Host "--------------------------------------------"
    Write-Host "Building Review..."
    Write-Host "--------------------------------------------"

    Invoke-GradleTask ":services:review:bootJar"

    Write-Host "--------------------------------------------"
    Write-Host "Building Profile..."
    Write-Host "--------------------------------------------"

    Invoke-GradleTask ":services:profile:bootJar"

    Write-Host "--------------------------------------------"
    Write-Host "All projects built successfully."
    Write-Host "Artifacts:"
    Write-Host " - contracts:  services/contracts/build/libs/"
    Write-Host " - eureka:  services/eureka/build/libs/"
    Write-Host " - gateway: services/gateway/build/libs/"
    Write-Host " - media:   services/media/build/libs/"
    Write-Host " - review: services/review/build/libs/"
    Write-Host " - profile: services/profile/build/libs/"
    Write-Host "--------------------------------------------"

    Write-Host "--------------------------------------------"
    Write-Host "Starting Docker Compose..."
    Write-Host "Press Ctrl + C to stop and run docker compose down."
    Write-Host "--------------------------------------------"

    $ComposeStarted = $true
    docker compose -f $ComposeFile up --build
    if ($LASTEXITCODE -ne 0) {
        throw "Docker Compose failed with exit code $LASTEXITCODE."
    }
}
finally {
    if ($ComposeStarted) {
        Write-Host ""
        Write-Host "--------------------------------------------"
        Write-Host "Stopping Docker Compose..."
        Write-Host "--------------------------------------------"

        docker compose -f $ComposeFile down
        if ($LASTEXITCODE -ne 0) {
            Write-Warning "Docker Compose cleanup failed with exit code $LASTEXITCODE."
        }
    }

    Pop-Location
}
