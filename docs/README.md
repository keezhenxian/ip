# Bestbot User Guide

![bestbot UI](docs/Ui.png)

Bestbot is a personal assistant chatbot inspired by *Duke*.  
It helps you keep track of tasks such as **todos, deadlines, and events**; you can mark/unmark, delete, find tasks, and it saves progress automatically.  
It supports both **CLI** and **GUI**.

---

## 📋 How to Use Bestbot

### 1. Adding todos
Adds a simple task without date/time attached.

**Command format:**
`todo <task name>"`

**Example Command:**  
`todo read book`

**Expected outcome:**  
`Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.`

---

### 2. Adding deadlines
Adds a task that must be done before a specific date.

**Command format:**  
`deadline <task name> /by <date>`

**Example Command:**
`deadline return book /by 2025-06-06`

**Expected outcome:**  
`Got it. I've added this task:
[D][ ] return book (by: Jun 6 2025)
Now you have 2 tasks in the list.`

---

### 3. Adding events
Adds a task that happens at a specific time.

**Command format:**
`event <event name> /from <time> /to <time>`

**Command:**  
`event project meeting /from 2025-06-06 2pm /to 3pm`

**Expected outcome:**  
`Got it. I've added this task:
[E][ ] project meeting (from: 2025-06-06 2pm to: 2pm)
Now you have 3 tasks in the list.`

---

### 4. Listing tasks
Displays all the tasks currently stored.

**Command:**  
list

**Expected outcome:**  
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (at: Mon 2pm)

---

### 5. Marking a task as done
Marks a task in the list as completed.

**Command Format**
`mark <task no. in list>`

**Example Command:**  
`mark 2`

**Expected outcome:**  
`Nice! I've marked this task as done:
[D][X] return book (by: Sunday)`

---

### 6. Marking a task as not done
Marks a previously completed task as not done.

**Command Format**
`unmark <task no. in list>`

**Example Command:**  
`unmark 2`

**Expected outcome:**  
`OK, I've marked this task as not done yet:
[D][ ] return book (by: Sunday)`

---

### 7. Deleting a task
Removes a task from the list.

**Command Format**
`delete <task no. in list>`

**Example Command:**  
`delete 3`

**Expected outcome:**  
`Noted. I've removed this task:
[E][ ] project meeting (at: Mon 2pm)
Now you have 2 tasks in the list.`

---

### 8. Finding tasks
Searches tasks whose descriptions contain a given keyword.

**Command Format**
`find <keyword>`

**Example Command:**  
`find book`

**Expected outcome:**  
`Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Jun 6 2025)`

---

### 9. Exiting the program
Closes the application.

**Command:**  
`bye`

**Expected outcome:**
`Bye. Hope to see you again soon!`


---

## 💾 Other Features

- **Saving tasks**  
  Bestbot automatically saves tasks to the hard disk after any modification (add, delete, mark, unmark). On restart, your tasks are loaded from the saved file.

- **Error handling**  
  Bestbot handles invalid inputs gracefully and provides clear error messages.

  **Example:**
  todo

  **Expected outcome:**  
  ☹ OOPS!!! The description of a todo cannot be empty.

---

## 🛠 Markdown Tips Used

- Headings made with `#`, `##`, `###` to organize sections.
- Code blocks use triple backticks (```) for commands and expected output.
- Bold (`**bold**`) and italic (`*italic*`) for emphasis.
- Lists (`-`) for clarity.
- Images embedded via `![alt text](path)` with relative links.

---

## 🙌 Thank You
Enjoy using Bestbot! 🚀  
If you find any bugs or want new features, feel free to open an issue or submit a PR.
