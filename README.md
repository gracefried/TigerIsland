# TigerIsland
CEN3031 Team Q's Project

## Git Workflow
1. `git checkout master` // Get on your local `master` branch
2. `git fetch upstream` // Fetch the team's repo
3. `git rebase upstream/master` // Rebase the team's changes (you want to keep all the current history!)
4. `git branch -l "myBranchName"` // Create a new local branch for the changes you want to make
5. `git checkout myBranchName` // Get on your newly created branch
6. Make some changes...
7. `git add .` // Add the changes. The . is just a file path, this add's the current directory
8. `git commit -m "I made some changes"` // Commit the changes. Leave a descriptive message.
9. `git push origin myBranchName` // Push your changes to the team's repo

Now, go to the [team's repo](https://github.com/CEN3031Q/TigerIsland) and submit a Pull Request. It'll get merged when we see all is good!
