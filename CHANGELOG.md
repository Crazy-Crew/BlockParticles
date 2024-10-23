### Additions:
- Added folia support (untested).

### Changes:
- Improved readability and slight performance increase by using single variables for commonly called methods.
- Save/load files such as `config.yml` or `data.yml` off the main thread.
  - This ensures that the main thread can run without issue, which is responsible for everything in some way.
- No longer store the player object in a hashmap, reduces memory footprint since we use the uuid (I don't know if `memory footprint` is the right word, Correct me if I am wrong.)
- Updated how the inventory that displays particles or fountain types is handled.
  - No longer check the inventory title to see if it's our inventory.
  - No longer check display name on items to find out which set of items to show a player.
  - This effectively keeps the menu secure from unwanted eyeballs.
- Updated to 1.21.1.
- Updated vital api.
- Updated the itembuilder.
  - With updating the ItemBuilder, and Vital API. You can expect more configuration to how the plugin works.
- Cleaned up unused methods.
- Send a message if the location you are trying to delete does not exist.
- End all for loops early, if we find what we need and utilize booleans to send messages depending on the outcome (true/false).

### Future Changes:
A current list of things I thought of that need to be done. If you have any suggestions, hop over to our issues tab!

- Ability to configure all messages.
  - The messages will be using MiniMessage, since we never had a configuration. I do not see a point in using legacy color codes.
- Ability to have control over what the particles look like.
  - The color, The amount of particles as an example!
- Better default storage, easier management for admins.