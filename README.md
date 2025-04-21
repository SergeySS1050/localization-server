`LinkoraLocalizationServer` powers [Linkora](https://github.com/sakethpathike/Linkora)'s remote strings, which can be updated without requiring an app update.

---

# How to Contribute Translations

All translations are now handled directly in this repo as JSON files. Crowdin is no longer used.

If you previously contributed via Crowdin (thank you!), your translations were saved before I deleted the account. I’ll add them here soon.


## Requirements

- A GitHub account.


## Step 1: Fork the Repository

Click the **Fork** button at the top-right of the repo page. That’s your own copy to work on.


## Step 2: Get Your Language Code

You’ll need the 3-letter ISO 639-2 code for your language.

1. Go to this page: [Wikipedia – ISO 639 Codes](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes)
2. Use the code from the `Set 3` column (e.g., `eng`, `fra`, `spa`)
3. We’ll call this `YOUR_LANGUAGE_CODE` from now on.


## Step 3: Use the Web Translation Editor

### 1. Check if the file already exists

Try this URL (replace with your language code):  
`https://linkoralocalizationserver.onrender.com/contribute?lang=YOUR_LANGUAGE_CODE`

- If you see a 404 or similar error, the file doesn’t exist yet.
- If the editor loads, the file already exists.

### 2. Open the editor

- If the file **exists**:  
  Use -> `https://linkoralocalizationserver.onrender.com/contribute?lang=YOUR_LANGUAGE_CODE`

- If the file **does not exist**:  
  Use -> `https://linkoralocalizationserver.onrender.com/contribute?newLang=true`

Using `?lang=...` for a non-existent file won’t load the editor.

### 3. Translate

In the editor you’ll see:

- `key`: the internal ID
- `value`: the English source string
- a text box: where you enter your translation

If you're editing an existing language, the box will be pre-filled. If it’s a new one, it’ll be empty.

**Keep placeholders like** `{#LINKORA_PLACE_HOLDER_1#}`, `{#LINKORA_PLACE_HOLDER_2#}`, **and similar numbered variants exactly as they are.**  
Don’t translate, rename, or modify them in any way. These represent dynamic values that the app needs at runtime.

### 4. Copy the JSON

Once done, click the `Copy JSON` button to copy your work to clipboard.


## Step 4: Add or Update the File in Your Fork

In your fork, go to:  
`src/main/resources/raw/`

### For a new language

- Create a new file named `YOUR_LANGUAGE_CODE.json`
- Paste the JSON you copied

### For an existing language

- Open `YOUR_LANGUAGE_CODE.json`
- Replace all its content with the updated JSON


## Step 5: Create a Pull Request

1. Commit your changes with a clear message, like  
   `feat: add French (fra) translation`  
   or  
   `fix: update Spanish (spa) translations`

2. Push to your fork

3. On GitHub, you’ll see a prompt to open a Pull Request

4. Click **Compare & pull request**, fill in the title/description

5. Submit the PR


Thank you to all the translators for taking the time to add valuable translations (yk I really appreciate it 😺🤝).