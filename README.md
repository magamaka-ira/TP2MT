# TP2MT - Spring Boot - Inversion of Control & Dependency Injection

> **Projet complet des 4 exercices du TP2 Spring Boot**  
> Étudiant: Moneli AGAMAKA | Promotion: IRA2026  
> Date: 27 Mai 2026

---

## 📁 **STRUCTURE DU PROJET**

```
TP2MT/
│
├── Exo1-DI/                          # Injection de Dépendances
│   ├── pom.xml
│   ├── .gitignore
│   └── src/main/
│       ├── java/com/example/demo/
│       │   ├── service/
│       │   │   ├── MessageService.java           (interface)
│       │   │   ├── EmailService.java             (@Component @Primary)
│       │   │   └── SmsService.java               (@Component)
│       │   └── app/
│       │       ├── NotificationService.java      (@Autowired setter)
│       │       └── App.java                      (classe principale)
│       └── resources/
│           └── application.properties
│
├── Exo2-Profils/                     # Profils Spring
│   ├── pom.xml
│   ├── .gitignore
│   └── src/main/
│       ├── java/com/example/demo/
│       │   ├── service/
│       │   │   ├── MessageService.java           (interface)
│       │   │   ├── DevMessageService.java        (@Profile("dev"))
│       │   │   └── ProdMessageService.java       (@Profile("prod"))
│       │   └── app/
│       │       ├── NotificationService.java      (@Autowired)
│       │       └── App.java                      (classe principale)
│       └── resources/
│           └── application.properties            (spring.profiles.active=dev)
│
├── Exo3-Circular/                    # Injection Circulaire
│   ├── pom.xml
│   ├── .gitignore
│   └── src/main/
│       ├── java/com/example/demo/
│       │   ├── circular/
│       │   │   ├── ClassA.java                  (@Autowired @Lazy ClassB)
│       │   │   └── ClassB.java                  (@Autowired ClassA)
│       │   └── app/
│       │       └── App.java                      (classe principale)
│       └── resources/
│           └── application.properties
│
├── Exo4-EscapeRooms/                 # Escape Rooms avec Profils
│   ├── pom.xml
│   ├── .gitignore
│   └── src/main/
│       ├── java/org/sebsy/demo/escaperooms/
│       │   ├── bll/
│       │   │   ├── RoomService.java             (interface)
│       │   │   ├── Room1Service.java            (@Profile("passage") @Primary)
│       │   │   ├── Room2Service.java            (@Profile("passage"))
│       │   │   ├── TreasureRoomService.java     (@Profile("treasure"))
│       │   │   └── TrapService.java             (@Profile("trap"))
│       │   ├── controller/
│       │   │   └── GameController.java          (test des salles)
│       │   └── EscapeRoomsApplication.java      (classe principale)
│       └── resources/
│           └── application.properties            (spring.profiles.active=passage,treasure)
│
└── README.md                          # Ce fichier

```

---

## 📚 **DESCRIPTION DÉTAILLÉE DES EXERCICES**

### 🔵 **EXERCICE 1 - INJECTION DE DÉPENDANCES (@Component, @Autowired, @Primary)**

**Objectif:** Maîtriser l'injection automatique de dépendances avec Spring

**Concepts clés:**
- `@Component` - Déclare une classe comme un bean Spring automatiquement géré
- `@Autowired` - Injecte automatiquement les dépendances (sur setter ici)
- `@Primary` - Indique le bean par défaut si plusieurs implémentations existent
- Injection par setter (vs constructeur)

**Architecture:**
```
MessageService (interface)
├── EmailService (@Component @Primary)     ← Injecté par défaut
└── SmsService (@Component)

NotificationService (@Component)
└── @Autowired setMessageService(MessageService)  ← Reçoit EmailService
```

**Fonctionnement détaillé:**
1. Spring démarre et scanne `com.example.demo` (scanBasePackages)
2. Trouve 3 beans: EmailService, SmsService, NotificationService
3. Lors de la création de NotificationService:
   - Spring cherche un bean de type MessageService à injecter
   - Trouve 2 candidats: EmailService et SmsService
   - EmailService a @Primary → sélectionné par défaut
   - Appelle: `notificationService.setMessageService(emailService)`
4. NotificationService utilise EmailService pour afficher le message

**Lancer:**
```bash
cd ~/Documents/TP2MT/Exo1-DI
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App"
```

**Résultat attendu:**
```
📢 Notification: Message envoyé par email.
✅ Service injecté: EmailService
```

---

### 🟠 **EXERCICE 2 - PROFILS SPRING (@Profile, spring.profiles.active)**

**Objectif:** Activer différentes implémentations selon l'environnement (dev/prod)

**Concepts clés:**
- `@Profile("dev")` - Active le bean uniquement si profil "dev" est actif
- `@Profile("prod")` - Active le bean uniquement si profil "prod" est actif
- `spring.profiles.active` - Configure les profils actifs dans properties
- Injection conditionnelle par environnement

**Architecture:**
```
MessageService (interface)
├── DevMessageService (@Profile("dev"))     ← Uniquement si profil=dev
└── ProdMessageService (@Profile("prod"))   ← Uniquement si profil=prod

NotificationService (@Component)
└── @Autowired MessageService  ← Dev ou Prod selon le profil
```

**Fonctionnement (Mode DEV):**
1. `spring.profiles.active=dev` dans application.properties
2. Spring crée uniquement DevMessageService (ProdMessageService est ignoré)
3. NotificationService injecte DevMessageService
4. Affiche: "🔧 [DEV] Mode développement - Messages de débogage activés"

**Fonctionnement (Mode PROD):**
1. Lancer avec `-Dspring.profiles.active=prod`
2. Spring crée uniquement ProdMessageService
3. Affiche: "✅ [PROD] Mode production - Sécurisé et optimisé"

**Lancer (DEV - par défaut):**
```bash
cd ~/Documents/TP2MT/Exo2-Profils
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App"
```

**Lancer (PROD):**
```bash
cd ~/Documents/TP2MT/Exo2-Profils
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App" -Dspring.profiles.active=prod
```

**Résultats attendus:**

Mode DEV:
```
📢 🔧 [DEV] Mode développement - Messages de débogage activés
✅ Service actif: DevMessageService
```

Mode PROD:
```
📢 ✅ [PROD] Mode production - Sécurisé et optimisé
✅ Service actif: ProdMessageService
```

---

### 🟡 **EXERCICE 3 - INJECTION CIRCULAIRE (@Lazy)**

**Objectif:** Résoudre les dépendances circulaires avec @Lazy

**Concepts clés:**
- Injection circulaire: ClassA dépend de ClassB et ClassB dépend de ClassA
- `@Lazy` - Crée le bean uniquement quand il est réellement utilisé (lazy loading)
- Résolution des dépendances complexes

**Architecture problématique (SANS @Lazy):**
```
ClassA
└── @Autowired ClassB  ← Crée ClassB
    └── @Autowired ClassA  ← Veut créer ClassA... mais elle est en cours de création!
        └── ERREUR: Circular dependency!
```

**Architecture résolue (AVEC @Lazy):**
```
ClassA
└── @Autowired @Lazy ClassB  ← Ne crée pas ClassB immédiatement
    
ClassB
└── @Autowired ClassA  ← Reçoit la référence à ClassA (déjà créée)

Quand on utilise ClassB → Spring le crée en ce moment
```

**Fonctionnement détaillé:**
1. Spring crée ClassA (sans créer ClassB grâce à @Lazy)
2. Spring crée ClassB (qui peut recevoir ClassA car elle existe)
3. Quand ClassA accède à ClassB → Spring le crée à ce moment
4. Pas d'erreur circulaire!

**Lancer:**
```bash
cd ~/Documents/TP2MT/Exo3-Circular
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App"
```

**Résultat attendu:**
```
✓ ClassA créée avec injection de ClassB
✓ ClassB créée avec injection de ClassA

🔵 ClassA - Je dépends de ClassB: ClassB
🟠 ClassB - Je dépends de ClassA: ClassA
```

---

### 🟢 **EXERCICE 4 - ESCAPE ROOMS (Multiple Implementations + Profils)**

**Objectif:** Combiner plusieurs concepts: multiple implementations, @Profile, @Primary

**Concepts clés:**
- Plusieurs implémentations d'une interface
- `@Profile` pour activer différentes salles selon la configuration
- `@Primary` pour désigner la salle par défaut
- GameController qui utilise la salle active

**Architecture:**
```
RoomService (interface)
├── Room1Service (@Profile("passage") @Primary)    ← Passage secret
├── Room2Service (@Profile("passage"))             ← Clé trouvée
├── TreasureRoomService (@Profile("treasure"))     ← Trésor
└── TrapService (@Profile("trap"))                 ← Piège

GameController
└── @Autowired RoomService  ← Injecte Room1Service (car @Primary)
```

**Profils configurés par défaut:**
```properties
spring.profiles.active=passage,treasure
```

Cela signifie:
- Room1Service: CRÉÉ (passage + @Primary)
- Room2Service: CRÉÉ (passage)
- TreasureRoomService: CRÉÉ (treasure)
- TrapService: IGNORÉ (trap pas actif)

**Lancer:**
```bash
cd ~/Documents/TP2MT/Exo4-EscapeRooms
mvn clean compile exec:java -Dexec.mainClass="org.sebsy.demo.escaperooms.EscapeRoomsApplication"
```

**Résultat attendu:**
```
🎮 ========== EXO4 - ESCAPE ROOMS ==========

📌 TEST: Jeu avec profils 'passage' et 'treasure'
🎮 🚪 Room 1 - Passage secret découvert!

✅ ========== EXO4 TERMINÉ ==========
```

---

## 🚀 **COMMANDES POUR LANCER CHAQUE EXERCICE**

### Exo1 - Injection de dépendances:
```bash
cd ~/Documents/TP2MT/Exo1-DI
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App"
```

### Exo2 - Profils (DEV):
```bash
cd ~/Documents/TP2MT/Exo2-Profils
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App"
```

### Exo2 - Profils (PROD):
```bash
cd ~/Documents/TP2MT/Exo2-Profils
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App" -Dspring.profiles.active=prod
```

### Exo3 - Injection circulaire:
```bash
cd ~/Documents/TP2MT/Exo3-Circular
mvn clean compile exec:java -Dexec.mainClass="com.example.demo.app.App"
```

### Exo4 - Escape Rooms:
```bash
cd ~/Documents/TP2MT/Exo4-EscapeRooms
mvn clean compile exec:java -Dexec.mainClass="org.sebsy.demo.escaperooms.EscapeRoomsApplication"
```

---

## 📊 **RÉSUMÉ DES CONCEPTS**

| Exercice | Concepts clés | Annotations | Statut |
|----------|---------------|-------------|--------|
| **Exo1** | DI basique | @Component, @Autowired, @Primary | ✅ |
| **Exo2** | Profils conditionnels | @Profile, spring.profiles.active | ✅ |
| **Exo3** | Dépendances circulaires | @Lazy, @Autowired | ✅ |
| **Exo4** | Multiple implementations | @Profile, @Primary, @Component | ✅ |

---

## 🎓 **POINTS D'APPRENTISSAGE**

✅ Inversion of Control (IoC) - Spring gère la création des objets  
✅ Dependency Injection (DI) - Les dépendances sont injectées automatiquement  
✅ @Component et @Autowired - Les annotations pour l'IoC/DI  
✅ @Primary - Désigner le bean par défaut  
✅ @Profile - Activer/désactiver les beans selon l'environnement  
✅ @Lazy - Résoudre les dépendances circulaires  
✅ spring.profiles.active - Configurer les profils actifs  
✅ CommandLineRunner - Exécuter du code au démarrage de l'application  

---

## 📸 **PREUVES DE FONCTIONNEMENT**

### Exo1 - Injection de dépendances:


<img width="2030" height="318" alt="image" src="https://github.com/user-attachments/assets/35d95a9d-cb1f-4101-b7c0-948202f0691e" />


### Exo2 Dev


<img width="1015" height="159" alt="image" src="https://github.com/user-attachments/assets/88736a5f-14c8-4a8c-95d3-42317790b30b" />



### Exo3 - Injection circulaire:


<img width="1015" height="159" alt="image" src="https://github.com/user-attachments/assets/82160aef-fd70-47aa-901e-81143328b721" />


### Exo4 - Escape Rooms:



<img width="1015" height="159" alt="image" src="https://github.com/user-attachments/assets/3f777937-7386-4a60-9429-a22acd21bf66" />

---

## ✅ **

TP2MT complet avec les 4 exercices 
- Injection simple avec @Primary
- Injection conditionnelle avec @Profile
- Résolution des dépendances circulaires avec @Lazy
- Multiple implementations avec une salle par défaut

**Étudiant:** Moneli AGAMAKA  
**Promotion:** IRA2026  
**Date:** 27 Mai 2026
