# Aspect-Oriented Programming

Az **Aspect Oriented Programming** (AOP) a Spring keretrendszerben egy olyan technika, amely segít külön kezelni a keresztmetszeti feladatokat, mint például a naplózás, tranzakciókezelés, és hibakezelés, anélkül, hogy a fő üzleti logikát megzavarnánk. A Spring AOP használatával az ilyen típusú kódok külön aspektusokba (aspects) szervezhetők, így az üzleti logika tisztább és karbantarthatóbb marad.

### Alapfogalmak:
- **Aspect**: A keresztmetszeti feladatokat megvalósító modul, amely tartalmazza a tanácsadási (advice) logikát.
- **Advice**: Az a kód, amit az aspektus végrehajt egy meghatározott ponton (pl. módszer hívás előtt vagy után).
- **Join Point**: A kód végrehajtásának egy pontja, ahol aspektust lehet végrehajtani (pl. metódushívás).
- **Pointcut**: Az a kritérium, ami meghatározza, hogy egy adott advice mely join pointokon hajtódjon végre.

### Különböző Advice Típusok
1. **@Before**: A módszer végrehajtása előtt fut le. Gyakran használják ellenőrzésekhez vagy naplózáshoz.
   ```java
   @Before("execution(* com.example.service.*.*(..))")
   public void beforeMethod(JoinPoint joinPoint) {
       System.out.println("Method started: " + joinPoint.getSignature().getName());
   }
   ```
2. **@After**: A módszer lefutása után hívódik meg, függetlenül attól, hogy a metódus sikeresen végrehajtódott-e vagy kivételt dobott. Ezt jellemzően naplózáshoz használják.
   
   ```java
    @After("execution(* com.example.service.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("Method completed: " + joinPoint.getSignature().getName());
    }
   ```
4. **@AfterReturning**: Csak akkor fut le, ha a módszer sikeresen befejeződött (kivétel nélkül). Ezt gyakran használják a visszatérési érték naplózásához vagy manipulálásához.
   ```java
    @AfterReturning(pointcut = "execution(* com.example.service.*.*(..))", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        System.out.println("Method returned: " + result);
    }
   ```
5. **@AfterThrowing**: Az egyik legrugalmasabb advice típus, amely a módszer végrehajtása előtt és után is fut. Lehetőséget biztosít a metódus végrehajtásának megszakítására, módosítására vagy éppen teljes helyettesítésére. Gyakran használják tranzakciókezeléshez vagy időmérési feladatokhoz.
   ```java
    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "error")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable error) {
        System.out.println("Exception thrown: " + error);
    }
   ```
6. **@Around**: A módszer végrehajtása előtt fut le. Gyakran használják ellenőrzésekhez vagy naplózáshoz.
   ```java
    @Around("execution(* com.example.service.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Method execution starts: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed(); // eredeti metódus meghívása
        System.out.println("Method execution ends: " + joinPoint.getSignature().getName());
        return result;
    }
   ```

   ### Használati területek
- **Naplózás**: Az összes metódushívás naplózása, hogy később elemezhető legyen, hol és mikor történt hiba vagy milyen adatokkal dolgozott a rendszer.
- **Tranzakciókezelés**: Az AOP segítségével tranzakciókat lehet definiálni olyan metódusok köré, amelyek adatbázis műveleteket hajtanak végre.
- **Hitelesítés és Jogosultságkezelés**: Az AOP lehetőséget ad, hogy egyes metódusokat csak akkor hajtsunk végre, ha a felhasználó rendelkezik a szükséges jogosultságokkal.
- **Kivételkezelés**: Biztosítja, hogy a hibák következetesen legyenek naplózva és kezelve minden metódusban.

### Ezek az aspektusok segítik az üzleti logikától elkülöníteni azokat a feladatokat, amelyek ismétlődnének, így átláthatóbb és karbantarthatóbb kódot eredményeznek.
