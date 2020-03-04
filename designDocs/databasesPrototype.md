# Users
user_id
username
password
email (optional)

# Games
game_id
title
system
user_id (fk)

# Playthroughs
playthrough_id
playthrough_number
percentage
status
user_id (fk)
game_id (fk)
