# Course Finder

Functional API login, JSON data retrieval application 

---

## Overview

Course Finder is a Kotlin based Android 8.0+ application which demonstrates API functionality using HTTP GET and POST requests to verify logins, alongside retrieving JSON data about courses

---

## Tech Stack

- Language: Kotlin 
- UI: XML layouts
- Networking: Retrofit + Coroutines  
- Dependency Injection: Hilt  

---

## Features
- User login & authentication  
- Home dashboard  
- Live API data with error handling  
- Details page with it's own data class

---

## Project Structure

https://i.imgur.com/qeU0LeX.png

(The tree Unicode text wouldn't embed properly)

---

## Installation & Setup

For Android Studio:
1. Clone this repository in the android studio terminal:
   git clone https://github.com/mitchellherden05/s8130336_assignment2

2. Open the project

3. Sync gradle and dependencies

4. Run the application using an emulator

**IMPORTANT:**
If the virtual machine the API is using hasn't been activated, loading times may vary

---

## Usage
1. Log into the application using the required text fields

	Username: mitchell
	Password: 8130336

2. You'll be automatically redirected to the dashboard fragment

3. Press any courses' "details" button to reveal the details of the selected course

4. To return to the dashboard, press the backwards facing arrow above the emulated device

---

## Permissions

- "android.permission.INTERNET"
- "android.permission.ACCESS_NETWORK_STATE"



