# Contributing Guide

[貢獻指南 (Chinese Version)](CONTRIBUTING.zh-Hant.md)

---

## Submitting an Issue

Before you submit a new issue, please search existing issues to avoid duplicates.

When submitting an issue, please provide the following information:

- **Title**: A brief and descriptive title for your issue.
- **Steps to Reproduce**: If it's a bug report, please provide detailed steps to reproduce the issue. This helps us fix it faster.
- **Expected vs. Actual Result**: Describe what you expected to happen and what actually occurred.
- **Environment Information**: Include details about your environment, such as your browser, operating system, and version numbers.

---

## Code Contribution Guide

### 1. Branching Convention

Please create your feature branch from the `main` branch. We recommend the following naming convention for your branches:

- `feat/issue-number-short-description` (for new features)
- `fix/issue-number-short-description` (for bug fixes)
- `docs/issue-number-short-description` (for documentation changes, like README or other guides)
- `chore/issue-number-short-description` (for maintenance, like CI/CD configurations or build process changes)

**Examples**:

- `feat/issue-123-add-login-button`
- `docs/issue-456-update-contributing-guide`
- `chore/issue-789-update-build-config`

### 2. Commit Message Convention

We follow the **[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)** specification. This helps us automatically generate release notes and version numbers. Please ensure each of your commit messages adheres to the following format:

```text
<type>(<scope>): <description>

[optional body]

[optional footer(s)]
```

- **`type`**: Required. Describes the main purpose of the commit. Common types are:
  - `feat`: A new feature
  - `fix`: A bug fix
  - `docs`: Documentation changes
  - `style`: Formatting, semicolons, etc. (no code changes)
  - `refactor`: Code refactoring (neither adds a feature nor fixes a bug)
  - `perf`: A code change that improves performance
  - `test`: Adding or correcting tests
  - `chore`: Changes to the build process or auxiliary tools and libraries

- **`scope`**: Optional. Describes the part of the codebase affected by the change (e.g., `(login)` or `(api)`).
- **`subject`**: Required. A short, concise description of the change.

**Examples**:

```text
feat(module): create xxx module
fix(api): fix 500 server error
chore(ci): edit workflow yml
```

**Breaking Changes**:
If your changes include any content that **breaks backward compatibility**, you must explicitly mark this change.
This will trigger a major version update and follows the [Semantic Versioning](https://semver.org/) specification.
Add a `!` after the `<type>` or `<scope>` in the commit header.

**Example**:

```text
feat(api)!: remove old user client
```

**Commit Body**
The `body` is optional and is used to provide a detailed description of the commit. Use it when your changes require more context.

- **Purpose**: Explain **why** this change is important and **how** it solves the problem.
- **Format**: The body should start after a blank line following the subject line. When the change involves multiple items, it is recommended to use an **unordered list** for better readability.

**Example**:

```text
fix(checkout): resolve multiple bugs on the checkout page

- Fixed an issue where the total price did not update in real-time when the user changed quantities on the checkout page.
- Fixed a bug where the checkout button was still clickable when there were no items in the shopping cart.
- Added form validation to ensure the address and phone number formats entered by the user are correct.
```

**Footer**
The `footer` is optional and is used to link **Issues** or **mark breaking changes**. Each footer should have a token, followed by a subject, and end with a newline.

- **Purpose**: Provide structured metadata, such as Issue references.
- **Format**: The footer should start after a blank line following the body.

**Common Uses**:

- **Referencing Issues**: Used to associate the commit with the Issues it fixes or relates to.
  **Example**: `Close #123`
- **Breaking Changes**: You can also add `BREAKING CHANGE:` at the end of the commit description to provide more detailed information.
  **Example**:

  ```text
  refactor!: correct password validation logicThis

  fix changes the password encryption algorithm. All users will need to reset their passwords to log in.

  BREAKING CHANGE: All old passwords will become invalid; users need to reset them.
  ```

### 3. Pull Request (PR) Guidelines

When your feature is complete and ready to be merged, create a pull request.

The PR title must follow the **Conventional Commits** specification so that automated tools can parse it correctly. The title format is:

`<type>(<scope>): <subject> (#<issue number>)`

- `<type>`: Indicates the nature of the change, for example:
  - `feat`: A new feature
  - `fix`: A bug fix
  - `docs`: Documentation changes
  - `style`: Code style changes (does not affect logic)
  - `refactor`: Code refactoring
  - `perf`: Performance optimization

- `<scope>` (optional): Indicates the module or scope affected by the change.
- `<subject>`: Briefly describes the content of the change.
- `(#<issue number>)`: Clearly links to the relevant Issue.

**Example: `feat(auth): add user login feature (#123)`**

In the PR description, please detail:

- **Relevant Issue Number**: Link to the related issue, e.g., `Closes #123`.
- **Summary of Changes**: A brief summary of what was changed.
- **Testing Information**: A description of how you tested your changes.

At least one reviewer must approve your pull request before it can be merged.

---
