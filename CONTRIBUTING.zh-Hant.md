# 貢獻指南

[Contributing Guid (英文版)](CONTRIBUTING.md)

---

## Issue 提交規範

在您提交新的 Issue 前，請先搜尋現有的 Issue，以避免重複。

提交 Issue 時，請提供以下資訊：

* **標題**：請簡要且具體地描述您的問題或建議。
* **重現步驟**：如果是 Bug 回報，請提供詳細的重現步驟。這有助於我們更快地解決問題。
* **預期結果與實際結果**：說明您預期會看到什麼，以及實際發生了什麼。
* **環境資訊**：請註明您使用的瀏覽器、作業系統版本等相關資訊。

---

## 程式碼提交規範

### 1. 分支命名規範

請從 `main` 分支建立您的功能分支。我們建議以下分支命名格式：

* `feat/issue-編號-簡短描述` (新功能)
* `fix/issue-編號-簡短描述` (Bug 修正)
* `docs/issue-編號-簡短描述` (文件變動，如 README 或其他說明文件)
* `chore/issue-編號-簡短描述` (日常維護，如 CI/CD 設定、建置流程或輔助工具的變動)

**範例**：

* `feat/issue-123-add-login-button`
* `docs/issue-456-update-contributing-guide`
* `chore/issue-789-update-build-config`

### 2. Commit 訊息規範

我們遵循 **[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/zh-hans/)** 規範，這有助於自動產生版本日誌和版本號。請確保您的每個 Commit 訊息都遵循以下格式：

```text
<type>(<scope>): <description>
[optional body]

[optional footer(s)]
```

* **`type` (類型)**: 必需，用於說明 Commit 的主要目的。常見類型如下：
  * `feat`: 新增功能 (feature)
  * `fix`: 修復 Bug
  * `docs`: 文件變更
  * `style`: 格式變動 (不影響程式碼邏輯，如空格、分號)
  * `refactor`: 程式碼重構 (不新增功能或修復 Bug)
  * `perf`: 效能優化
  * `test`: 新增或修改測試
  * `chore`: 日常維護、建置流程或輔助工具的變動

* `scope` **(範圍)**: 可選，用於說明本次變動影響的範圍，例如 `(login)` 或 `(api)`。
* `subject` **(主題)**: 必需，簡短描述本次變動。

**範例**：

```text
feat(login): 新增使用者登入按鈕
fix(api): 修正 API 傳回 500 的問題
chore(ci): 編輯 workflow yml
```

**破壞性變更（Breaking Changes）**:
如果您的變動包含任何**破壞向後相容性**的內容，您必須明確標記此變更。
這會觸發一次 major 的更新，並遵循 [Semantic Versioning](https://semver.org/). 規範。
請在 Commit 標題的 `<type>` 或 `<scope>` 後方加上 `!`。

**範例**：

```text
feat(api)!: 移除舊的使用者端
```

**Commit 本文 (Body)**
`body` 是可選的，用於提供關於本次 Commit 的詳細描述。當你的變更需要更多上下文時，可以使用它。

* **目的**：解釋「**為什麼**」這個變更很重要，以及它「**如何**」解決了問題。
* **格式**：本文應該在主題行之後空一行開始。當變動包含多個項目時，建議使用**無序列表**來分點說明，以提高可讀性。

**範例**：

```text
fix(checkout): 修正結帳頁面多個 Bug

- 修正當使用者在結帳頁面更改數量時，總價沒有即時更新的問題。
- 修正當購物車中沒有商品時，結帳按鈕仍然可點擊的 Bug。
- 增加了表單驗證，確保使用者輸入的地址和電話格式正確。
```

**頁腳 (Footer)**
`footer` 是可選的，用於連結 **Issue** 或**標記破壞性變更**。每個頁腳都應該有一個標頭，後面跟著一個主題，並在結尾處加上換行。

* **目的**：提供結構化的元數據，例如 Issue 參考。
* **格式**：頁腳應該在本文之後空一行開始。

**常見用途**：

* **參考 Issue**：用來關聯本次 Commit 所修復或相關的 Issue。
  **範例**： `Close #123`
* **破壞性變更（Breaking Changes）**: 您也可以在 Commit 描述的結尾，加上 `BREAKING CHANGE:` 來提供更詳細的說明。
  **範例**：

  ```text
  refactor!: 修正密碼驗證邏輯

  這個修正改變了密碼加密演算法，所有使用者都需要重設密碼才能登入。

  BREAKING CHANGE: 所有舊版密碼將失效，使用者需重新設定。
  ```

### 3. Pull Request (PR) 指南

當您的功能完成並準備好合併時，請建立一個 Pull Request。

PR 標題必須遵循 **Conventional Commits** 規範，以便自動化工具能正確解析。標題格式為：

`<type>(<scope>): <subject> (#<issue 編號>)`

* `<type>` (類型)：指明變更的性質，例如：
  * `feat`：新增功能
  * `fix`：修復 Bug
  * `docs`：文件變更
  * `style`：程式碼風格變更（不影響邏輯）
  * `refactor`：重構程式碼
  * `perf`：性能優化

* `<scope>` (範圍，可選)：指明變更所影響的模組或範圍。
* `<subject>` (主題)：簡潔地描述變更內容。
* `(#<issue 編號>)`：明確連結到相關的 Issue。

**範例：`feat(auth): 新增使用者登入功能 (#123)`**

在 PR 描述中，請詳細說明：

* **相關的 Issue 編號**：請連結相關的 Issue，例如 `Closes #123`。
* **變更內容**：簡要說明您所做的變更。
* **測試方式**：描述您如何測試這些變更。

在程式碼被合併前，至少需要一位 Code Reviewer 審核並通過。

---
