# Kspl_Demo (Selenium + TestNG + ExtentReports)

## Prerequisites (Windows)
- **Java JDK**: Java 8+ installed and `JAVA_HOME` set
- **Google Chrome** installed
- **Apache Maven** installed and on your `PATH` (this repo currently does **not** include `mvnw.cmd`)

### Install Maven (one option)
- **Chocolatey**:

```powershell
choco install maven -y
```

Then open a *new* terminal and verify:

```powershell
mvn -v
```

## Run tests
From the project folder:

```powershell
cd "C:\Users\Deku\Desktop\Kspl_Demo"
mvn test
```

## Extent report output
This project generates an Extent HTML report during `mvn test` via the TestNG listener `listeners.ExtentTestListener`.

- Output folder: `test-output`
- File name pattern: `ExtentReport_yyyy-MM-dd_HH-mm-ss.html`

