# mvvm-example-memo-android

MVVMのサンプル用のメモアプリ

## アプリ概要

### レイアウトと機能の策定

- MVVMのサンプルアプリなのでレイアウトは標準パーツを多用して簡素にした。
- ネットワークがなくても動作確認できるようにAPI通信ではなくDBを使うメモアプリにした。

### 画面構成

### A01 メモ一覧

- DBから取得したメモの一覧を表示する。
   - メモ一覧はDBの更新に追従する。
- 画面上部のSearchViewでメモの本文のインクリメンタルサーチを行う。
- 画面上部の+ボタンをタップすると`A02 メモ編集`に遷移する。
- RecyclerViewのItemをタップすると`A02 メモ編集`に遷移する。

### A02 メモ編集

- メモの本文の入力を行う
- 画面上部の保存ボタンをタップするとメモの新規作成または上書き更新を行い、`A02 メモ編集`に戻る。
  - `A01 メモ一覧`の画面上部の+ボタンをタップして遷移してきた場合はメモの新規作成を行う。
  - `A01 メモ一覧`のRecyclerViewのItemをタップして遷移してきた場合は選択したItemで表示していたメモの上書き更新を行う。
- 画面上部のゴミ箱のアイコンのボタンをタップするとメモの削除を行い、`A02 メモ編集`に戻る。

## 対応OS

- Android 6.0以上

## 開発環境

- Android Studio Arctic Fox

## ライブラリ

### 使用ライブラリ一覧

|ライブラリ|用途|
|--------|----|
|[Groupie](https://github.com/lisawray/groupie)|RecyclerViewの実装の省力化|
|[Koin](https://github.com/InsertKoinIO/koin)|DI|
|[Ktlint](https://plugins.gradle.org/plugin/org.jlleitschuh.gradle.ktlint)|Lintとフォーマット|
|[Room](https://developer.android.com/training/data-storage/room)|DB|

## アーキテクチャ

### MVVM

ViewModel → RepositoryのLayered Architectureを採用している。

#### ViewModel

ViewModelが外部に公開するのは以下の2つだけにする。

- FragmentへのOutputであるFlow
- FragmentからのInputである返り値を持たないfun

#### Repository

Repositoryはinterfaceを定義して、interfaceをViewModeにInjectすることで疎結合を保証する。

Repositoryのinterfaceは特定のライブラリに依存しないようにする。

## マルチモジュール

以下の2つのモジュールに分けている。

### appモジュール

ViewModel以上のものを配置している。

### coreモジュール

Repository以下のものを配置している。

Repositoryのinterfaceはpubicにしてappモジュールから参照できるようにする。

特定のライブラリに依存する実装はinternalにしてappモジュールから参照できないようにする。