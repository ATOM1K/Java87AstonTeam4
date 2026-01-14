@echo off
echo ============================================
echo ?? ??????? Java ?????? ?? ??????? BOM
echo ============================================
echo.

set checked=0
set withBOM=0

for /r src %%f in (*.java) do (
    set /a checked+=1
    
    rem ????????? PowerShell ??? ???????? ??????
    powershell -Command ^
        "$bytes = [System.IO.File]::ReadAllBytes('%%f');" ^
        "if ($bytes.Length -ge 3 -and $bytes[0] -eq 0xEF -and $bytes[1] -eq 0xBB -and $bytes[2] -eq 0xBF) {" ^
        "    Write-Host '? %%f - BOM ??????!' -ForegroundColor Red;" ^
        "    $withoutBom = $bytes[3..($bytes.Length-1)];" ^
        "    [System.IO.File]::WriteAllBytes('%%f', $withoutBom);" ^
        "    Write-Host '    -> BOM ??????' -ForegroundColor Green;" ^
        "    exit 1;" ^
        "}"
    
    if !errorlevel! equ 1 (
        set /a withBOM+=1
    )
)

echo.
echo ============================================
echo ????????:
echo ???????? ??????: %checked%
echo ?????? BOM: %withBOM%
echo ============================================
pause
