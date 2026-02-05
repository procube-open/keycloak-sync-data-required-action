# keycloak-sync-data-required-action

RequiredAction ```Sync Data``` の priority を一番低く設定することで、最後に実行されるようになります。

## Build

### Dockerを使用する方法（推奨）

環境を汚さずにビルドできます：

リポジトリ直下（この README.md があるディレクトリ）で以下のコマンドを実行してください。

```bash
docker build --output type=local,dest=./output .
```

ビルドが完了すると、`./output/keycloak-sync-data-required-action.jar` が生成されます。

### ローカル環境でビルドする方法

JDK 11以上とMavenがインストールされている場合：

リポジトリ直下（この README.md があるディレクトリ）で以下のコマンドを実行してください。

```bash
mvn clean package
```

ビルドが完了すると、`./target/keycloak-sync-data-required-action.jar` が生成されます。

## Usage

生成された `keycloak-sync-data-required-action.jar` の使い方については、Keycloak公式ドキュメントをご参照ください。

- [Keycloak Server Developer Guide](https://www.keycloak.org/docs/latest/server_development/#_providers)

このjarファイルはKeycloakのSPI（Service Provider Interface）としてデプロイできます。

## License

このプロジェクトは [Apache License 2.0](LICENSE) の下でライセンスされています。

## Acknowledgments & Credits

- [Keycloak](https://www.keycloak.org/) - このプロジェクトで使用されているオープンソースのアイデンティティおよびアクセス管理ソリューション

We are grateful to the communities behind them.
