object DeleteProductForm: TDeleteProductForm
  Left = 0
  Top = 0
  Caption = 'Supprimet un utilisateur'
  ClientHeight = 90
  ClientWidth = 324
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object lblTitreDeleteProduct: TLabel
    Left = 0
    Top = 0
    Width = 324
    Height = 30
    Align = alTop
    Alignment = taCenter
    Caption = 'Suppression d'#39'un produit'
    Constraints.MaxHeight = 30
    Constraints.MaxWidth = 324
    Constraints.MinHeight = 30
    Constraints.MinWidth = 324
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -20
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
  end
  object PanelBas: TPanel
    Left = 0
    Top = 60
    Width = 324
    Height = 30
    Align = alBottom
    Constraints.MaxHeight = 30
    Constraints.MaxWidth = 324
    Constraints.MinHeight = 30
    Constraints.MinWidth = 324
    TabOrder = 0
    object btnDeleteProduct: TButton
      Left = 88
      Top = 1
      Width = 145
      Height = 27
      Caption = 'Suppression du produit'
      TabOrder = 0
      OnClick = btnDeleteProductClick
    end
  end
  object PanelGauche: TPanel
    Left = 0
    Top = 30
    Width = 324
    Height = 30
    Align = alClient
    Constraints.MaxHeight = 30
    Constraints.MaxWidth = 324
    Constraints.MinHeight = 30
    Constraints.MinWidth = 324
    TabOrder = 1
    object Panel6: TPanel
      Left = 1
      Top = 1
      Width = 322
      Height = 24
      Align = alTop
      TabOrder = 0
      object lblchoixId: TLabel
        Left = 1
        Top = 1
        Width = 179
        Height = 22
        Align = alLeft
        Alignment = taCenter
        Anchors = [akLeft, akTop, akRight, akBottom]
        AutoSize = False
        Caption = 'Identifiant du produit : '
        EllipsisPosition = epEndEllipsis
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = []
        ParentFont = False
        ExplicitWidth = 144
        ExplicitHeight = 39
      end
      object cbIdProduct: TComboBox
        Left = 180
        Top = 1
        Width = 141
        Height = 21
        Align = alRight
        TabOrder = 0
      end
    end
  end
end
