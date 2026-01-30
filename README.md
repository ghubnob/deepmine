# FossilMine (working title)

Mini-game plugin for Minecraft (Paper/Bukkit 1.12.2) focused on layered mining and fossil excavation.

The core gameplay is built around a personal mine instance for each player, consisting of multiple geological layers with different materials and fossils. The player progresses deeper, unlocks new layers, and extracts valuable fossils using different tools.

---

## Gameplay Concept

- Each player is assigned a **personal mine** sized **10x10x50**
- The mine consists of **predefined layers**, each with its own material and thickness:
  - Dirt (4)
  - Stone (10)
  - Cobblestone (12)
  - Sandstone (10)
  - Netherrack (10)
  - End Stone (8)
  - Obsidian (1)
  - Adamantium (8) *(custom)*
  - Netherium (10) *(custom)*
  - Titan (10) *(custom)*
  - Sienite (10) *(custom)*

- Layers are generated **gradually**, one Y-level per server tick, to avoid TPS spikes.
- Each layer contains **fossils** of four rarities:
  - COMMON
  - RARE
  - EPIC
  - LEGENDARY

- Fossils are visually represented via **custom block textures** (resource pack).

---

## Mining & Progression

Players use different tools:
- **Pickaxe** – breaks all blocks and fossils, drops raw fossils (lower value)
- **Brush** (purchasable) – breaks only fossils, drops processed fossil items (higher value)
- **Magnifying Glass** (purchasable) – used to study fossils and increase their value

A tutorial is planned:
- Player receives all tools temporarily
- Learns core mechanics
- Obtains the first museum item
- After completion, only the pickaxe remains

---

## Mine Reset Logic

The mine has a physical depth limit.

When the player reaches Y=11:
- The player is briefly blinded
- Teleported to the top of the mine
- A new layer is generated above with updated depth progression

This allows infinite progression without increasing world size.

---

## Museum (Planned)

- Separate world/location
- Players can place processed fossils
- Generates passive income
- Fossils can be stolen if the owner is offline
- Protection can be purchased (planned as monetization feature)

---

## Technical Overview

### Core Classes

- **MineInstance**
  - Represents a single player mine
  - Owner (PlayerProfile)
  - ActiveLayer
  - Fossil map (location → FossilDefinition)
  - Region protection (block breaking outside mine is cancelled)

- **MineGenerator**
  - Generates mine layers incrementally

- **MinesManager**
  - Manages all mine instances
  - Assigns free mine locations
  - Stores UUID → MineInstance mapping

- **ActiveLayer**
  - Current layer being mined
  - Tracks remaining thickness
  - Handles block break logic per Y-level

- **MineLayer**
  - Layer template
  - Block representation
  - Default thickness

- **BlockRep**
  - Abstract block/item representation
  - Supports custom material + data
  - Produces block or item drops

- **FossilDefinition**
  - Fossil type (layer + rarity)
  - Spawn weight
  - Associated BlockRep

- **FossilService**
  - Rolls fossil spawn per block
  - Determines which fossil drops

---

## Player Data & Persistence

- **PlayerProfile**
  - UUID
  - Depth
  - Money
  - Current layer thickness

- **PlayerProfileManager**
  - ConcurrentHashMap cache
  - Loads profiles on join
  - Delegates persistence logic

- **PlayerProfileService**
  - Facade over profile operations
  - Contains complex logic

- **ProfileRepository (interface)**
  - load / save / init / close / isPlayerJoined

- **Implementations**
  - MySqlRepository (HikariCP, credentials in config.yml)
  - JsonRepository

---

## Version & Stack

- Paper / Bukkit **1.12.2**
- Java
- Resource pack for custom blocks
- No mods required

---

## Project Status

The project is under active development.
Core mechanics and architecture are implemented; some gameplay systems are planned but not finalized yet.
