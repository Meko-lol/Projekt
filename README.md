# 🗝️ Prisoner of the Depths

[![Source Code](https://img.shields.io/badge/GitHub-Meko--lol%2FProjekt-blue?logo=github)](https://github.com/Meko-lol/Projekt)

---

> *"The only way to freedom is the massive stone exit, which is however blocked by a Magic Boulder and guarded by the elite Guardians of the East."*

---

## ℹ️ About the Game

The game begins with the main hero waking up in a cold and damp cell deep underground. The character suffers from memory loss and does not remember how they entered this vast cave complex. The surroundings show signs of an ancient dwarven civilization that is now in ruins and inhabited by dangerous creatures.

You play as a prisoner whose main task is to escape the underground. To achieve this, you must explore the ruins, find a mysterious chest to obtain a magical weapon, and use specialized stations like the Forge or Shrine to repair and enchant it. The journey culminates in a final battle against the Guardians to destroy the boulder blocking your path.

---

## 🌟 Highlights

* **Procedural World Generation**: The map is randomly generated for each playthrough (minimum 8x8 locations) based on data from external files.
* **Data-Driven Architecture**: The game contains no "hard data" in the code; all rooms, items, and characters are loaded at startup from external JSON files.
* **Command Pattern Implementation**: Player input is processed using the Command Pattern, where every action is a separate class.
* **Strategic Survival**: Manage a strict 10kg inventory weight limit. You must choose wisely between tools, weapons, and consumables.
* **Environmental Obstacles**: Overcome flooded tunnels with a boat, cross chasms with a rope, or clear cave-ins with a pickaxe.
* **Unique Fail State**: Losing a fight does not end the game. You are moved back to the Prison and must recover your confiscated gear from a storage room to continue.

---

## 🚀 Usage Instructions

The game is controlled entirely via console text commands. Use the following commands to interact with the world:

| Command | Action |
|:---|:---|
| `go [direction]` | Move North, South, East, or West |
| `examine` | Display a detailed description of the current room and items |
| `take [item]` | Pick up an item (checks backpack weight limit) |
| `drop [item]` | Drop an item from your inventory onto the ground |
| `talk [character]` | Start a dialogue with an NPC |
| `use [item]` | Contextual action (e.g., drink a potion or use a boat) |
| `attack` | Start a combat round against an enemy |
| `block` | Defensive action during combat to reduce incoming damage |
| `sharpen` / `enchant` | Upgrade your weapon (only works in a Workshop or Shrine) |
| `hint` | Receive a smart hint based on your current game situation |
| `help` | Display a list of all available commands |
| `quit` | Safely exit the game |

---

## ⬇️ Requirements

To run and build this game locally, you will need:

* **Java** (JDK 11 or higher recommended)
* **Jackson Library**: Used for parsing the game's JSON data files.
