object CreateProductForm: TCreateProductForm
  Left = 0
  Top = 0
  Caption = 'Cr'#233'ation d'#39'un produit'
  ClientHeight = 276
  ClientWidth = 351
  Color = clBtnFace
  Constraints.MaxHeight = 315
  Constraints.MaxWidth = 367
  Constraints.MinHeight = 315
  Constraints.MinWidth = 367
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object lblTitreCreateUser: TLabel
    Left = 0
    Top = 0
    Width = 351
    Height = 24
    Align = alTop
    Alignment = taCenter
    Caption = 'Cr'#233'ation d'#39'un produit'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -20
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
    ExplicitWidth = 187
  end
  object Panel1: TPanel
    Left = 0
    Top = 24
    Width = 352
    Height = 211
    Align = alLeft
    TabOrder = 0
    object Panel4: TPanel
      Left = 1
      Top = 165
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 4
      object Label4: TLabel
        Left = 1
        Top = 1
        Width = 31
        Height = 13
        Align = alLeft
        Caption = 'Type :'
      end
      object EditType: TEdit
        Left = 32
        Top = 1
        Width = 317
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel5: TPanel
      Left = 1
      Top = 124
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 3
      object Label1: TLabel
        Left = 1
        Top = 1
        Width = 30
        Height = 13
        Align = alLeft
        Caption = 'Tags :'
      end
      object Edittags: TEdit
        Left = 31
        Top = 1
        Width = 318
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel6: TPanel
      Left = 1
      Top = 83
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 2
      object Label3: TLabel
        Left = 1
        Top = 1
        Width = 25
        Height = 13
        Align = alLeft
        Caption = 'Prix :'
      end
      object EditPrice: TEdit
        Left = 26
        Top = 1
        Width = 323
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel7: TPanel
      Left = 1
      Top = 42
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 1
      object Label5: TLabel
        Left = 1
        Top = 1
        Width = 80
        Height = 13
        Align = alLeft
        Caption = 'Nom du produit :'
      end
      object editProductName: TEdit
        Left = 81
        Top = 1
        Width = 268
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel8: TPanel
      Left = 1
      Top = 1
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 0
      object Label6: TLabel
        Left = 1
        Top = 1
        Width = 128
        Height = 13
        Align = alLeft
        Caption = 'Identifiant de la boutique :'
      end
      object editIdShop: TEdit
        Left = 129
        Top = 1
        Width = 220
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
  end
  object PanelBas: TPanel
    Left = 0
    Top = 235
    Width = 351
    Height = 41
    Align = alBottom
    TabOrder = 1
    object Button1: TButton
      Left = 97
      Top = 6
      Width = 145
      Height = 27
      Caption = 'Cr'#233'ation de produit'
      TabOrder = 0
      OnClick = Button1Click
    end
  end
end
