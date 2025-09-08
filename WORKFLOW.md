# GitHub Automated Workflow

This workflow aims to achieve a high degree of automation in development, testing, and deployment, while tracking progress in real-time through a project board. It transforms cumbersome steps into predictable automation, allowing the team to focus on creating value.

## 1. Issue Creation and Scheduling

**Process:** When a new `Issue` is created, automation generates an `Item` in GitHub Projects and sets its status to `Backlog`.

**Purpose:** All work has a clear starting point.

## 2. Development Phase

**Process:**

* The team manually changes the Item status from `Backlog` to `To Do`, indicating the task is ready to start.
* The developer claims the task, manually sets the Item status to `In Progress`, creates a new branch, and starts development. All commits must follow the **[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)** specification.

**Purpose:** The project board accurately reflects work progress.

## 3. PR Creation and Continuous Integration (CI)

**Process:**

* When a developer pushes code and creates a PR, automation changes the Item status to `In Review`.
* GitHub Actions immediately triggers the CI process (build and test).
* The PR description must include `Closes #<Issue Number>` to automatically close the Issue after merging.

**Purpose:** Ensure code quality and compliance before it enters the main branch.

## 4. Automated Release and QA Testing

**Process:**

* Only when the PR is approved and all CI checks pass is it allowed to be merged into the `main` branch.
* After the PR is merged, automation sets the Item status to `Done`.
* **Automation waits for a configurable period (e.g., 5 minutes) to batch process all PRs merged within a short time.**
  * **Note:** The configuration of this waiting time should consider **team size**, **development pace**, and **deployment frequency**.
  * **Considerations:**
    * **Small teams (1-5 people):** A shorter time (e.g., **5-15 minutes**) can be set to pursue high-frequency, small-batch deployments.
    * **Medium-sized teams (5-15 people):** Suitable to set **30 minutes to 1 hour** to balance deployment frequency and QA team workload.
    * **Large teams or complex projects:** Consider setting a longer time (e.g., **2-4 hours**), or even combining it with fixed deployment time points.
* After the batching period ends, automation triggers the CD process, deploying the latest code (including all PRs within the batch) to the Staging environment.
* Automation increments the **[Semantic Versioning (SemVer)](https://semver.org/)** number (PATCH, MINOR, MAJOR) based on Conventional Commits messages, and creates a Git Tag (e.g., `v1.2.3`).
* **Automation dynamically generates QA Test Issues based on the number of PRs merged in this batch:**
  * **If only one PR is merged:** The Issue title will be `QA Test: <PR Title>`.
  * **If multiple PRs are merged:** The Issue title will be `QA Test: Release Version v<Version Number>`, and the Issue description will list links to all merged PRs for tracking.
* The Issue status is automatically set to `In Test`.

**Purpose:** While ensuring the independence of each PR, optimize deployment frequency and reduce the number of QA test cycles.

## 5. Test Feedback and Bug Fixing

**Process:**

* If QA finds a Bug, they report it in the QA Issue and manually change its status to `Bug`.
* A new Bug Issue is created for this bug, following the development process in Step 2.
* After the Bug Issue's PR is merged, the QA team retests the original QA Issue to ensure the problem has been fixed.

**Purpose:** Establish a clear bug tracking and fixing cycle, ensuring problems are handled systematically.

## 6. Final Release

**Process:**

* When the QA Test Issue is confirmed to have no problems, manually change its status to `Done`.
  * Manually or automatically deploy the code from the `Staging` environment to the `Production` environment after all QA tests have passed.

**Purpose:** Ensure the version finally released to the production environment is stable and of high quality.
