# Copilot Instructions for AI Agents

## プロジェクト概要
- JavaによるWebアプリケーションのMVCアーキテクチャ。
- 主要ディレクトリ:
  - `WEB-INF/classes/tera/`: コントローラ、コマンド、リクエスト/レスポンス、ドメインモデル。
  - `WEB-INF/classes/dao/`: DAOファクトリ、DAO実装、DB接続設定。
  - `WEB-INF/classes/exp/`: 例外クラス。
  - `WEB-INF/jsp/`: JSPビュー。
  - `WEB-INF/lib/`: サーブレット/JSP APIライブラリ。

## 主要コンポーネントとパターン
- **コマンドパターン**: `tera/AbstractCommand`と各種コマンド（例: `AddProductCommand`）。
- **フロントコントローラ**: `tera/FrontServlet`と`ApplicationController`。
- **DAOファクトリパターン**: `dao/AbstractDaoFactory`、`OraDaoFactory`、`ProductsDao`。
- **リクエスト/レスポンスラッパー**: `RequestContext`/`ResponseContext`、Web用は`WebRequestContext`/`WebResponseContext`。

## データフロー
1. `FrontServlet`がリクエストを受け、`ApplicationController`へ委譲。
2. `CommandFactory`でコマンドを生成し、実行。
3. コマンドはDAOを利用してDB操作。
4. 結果を`ResponseContext`に格納し、JSPへフォワード。

## ビルド・デバッグ・テスト
- **ビルド**: javacで`WEB-INF/classes`配下をコンパイル。例:
  ```bash
  javac -classpath "WEB-INF/lib/*" -d WEB-INF/classes $(find WEB-INF/classes -name '*.java')
  ```
- **デプロイ**: Tomcat等のサーブレットコンテナに`WEB-INF`ごと配置。
- **テスト**: 専用テストコードは未発見。DB接続やコマンド単体テストは`main`メソッド追加で実施可能。
- **デバッグ**: 例外は`exp/`配下の独自例外でラップ。ログ出力は未実装（必要なら`System.out.println`で一時対応）。

## プロジェクト固有の注意点
- **プロパティファイル**: DAOやコマンドの設定は`dao.properties`や`commands.properties`で管理。
- **JSP**: ビューは`WEB-INF/jsp/`配下。直接アクセス不可、必ずサーブレット経由。
- **依存ライブラリ**: `WEB-INF/lib/`のJARをクラスパスに追加。
- **例外処理**: すべてのDB/コマンド例外は`exp/`の例外でラップ。

## 参考ファイル
- `WEB-INF/classes/tera/FrontServlet.java`（フロントコントローラ）
- `WEB-INF/classes/tera/CommandFactory.java`（コマンド生成）
- `WEB-INF/classes/dao/AbstractDaoFactory.java`（DAOファクトリ）
- `WEB-INF/classes/exp/IntegrationException.java`（例外ラッパー）

---
この内容で不明点や追加したい情報があればご指摘ください。