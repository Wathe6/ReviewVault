Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

$RootDir = Resolve-Path "$PSScriptRoot\..\.."
Push-Location $RootDir

try {
    docker compose -f infrastructure/docker-compose.infra.yaml run --rm keycloak-init
    if ($LASTEXITCODE -ne 0) {
        throw "Keycloak initialization failed with exit code $LASTEXITCODE."
    }
}
finally {
    Pop-Location
}
