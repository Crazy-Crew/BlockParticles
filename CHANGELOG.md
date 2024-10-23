### Changes:
- Updated default config.yml to use HeadDatabaseAPI head ids. /hdb search <head> when the plugin is installed.
  - We no longer support the old way of getting ids.
```yaml
  heads:
    plants:
      #Tomato
      - '1130'
      #Mango
      - '32596'
      #Cherry
      - '31879'
      #Melon
      - '20577'
      #Mushroom
      - '21906'
      #Orange
      - '59223'
      #Golden Apple
      - '79863'
```
- Properly cancel all active particles on reload, and clean up our garbage.

### Fixed:
- Not getting tab completion with the permission `bparticles.admin`, because the check for the permission was if you had the permission.
- Fixed an issue with custom heads from HeadDatabaseAPI.
  - We load everything after the HeadDatabaseAPI's database loads if the plugin is enabled.