object UpdateProductForm: TUpdateProductForm
  Left = 0
  Top = 0
  Caption = 'UpdateProductForm'
  ClientHeight = 301
  ClientWidth = 354
  Color = clBtnFace
  Constraints.MaxHeight = 340
  Constraints.MaxWidth = 370
  Constraints.MinHeight = 340
  Constraints.MinWidth = 370
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object lblTitreUpdateProduct: TLabel
    Left = 0
    Top = 0
    Width = 354
    Height = 24
    Align = alTop
    Alignment = taCenter
    Caption = 'Mise '#224' jour d'#39'un produit'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -20
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
    ExplicitWidth = 210
  end
  object Panel1: TPanel
    Left = 0
    Top = 24
    Width = 352
    Height = 236
    Align = alLeft
    TabOrder = 0
    object Panel4: TPanel
      Left = 1
      Top = 189
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 5
      object Label4: TLabel
        Left = 1
        Top = 1
        Width = 31
        Height = 39
        Align = alLeft
        Caption = 'Type :'
        ExplicitHeight = 13
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
      Top = 148
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 4
      object Label1: TLabel
        Left = 1
        Top = 1
        Width = 30
        Height = 39
        Align = alLeft
        Caption = 'Tags :'
        ExplicitHeight = 13
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
      Top = 107
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 3
      object Label3: TLabel
        Left = 1
        Top = 1
        Width = 25
        Height = 39
        Align = alLeft
        Caption = 'Prix :'
        ExplicitHeight = 13
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
      Top = 66
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 2
      object Label5: TLabel
        Left = 1
        Top = 1
        Width = 80
        Height = 39
        Align = alLeft
        Caption = 'Nom du produit :'
        ExplicitHeight = 13
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
      Top = 25
      Width = 350
      Height = 41
      Align = alTop
      TabOrder = 1
      object Label6: TLabel
        Left = 1
        Top = 1
        Width = 128
        Height = 39
        Align = alLeft
        Caption = 'Identifiant de la boutique :'
        ExplicitHeight = 13
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
    object Panel2: TPanel
      Left = 1
      Top = 1
      Width = 350
      Height = 24
      Align = alTop
      TabOrder = 0
      object lblchoixId: TLabel
        Left = 1
        Top = 1
        Width = 128
        Height = 22
        Align = alLeft
        AutoSize = False
        Caption = 'Identifiant du produit : '
        EllipsisPosition = epEndEllipsis
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = []
        ParentFont = False
      end
      object cbIdProduct: TComboBox
        Left = 129
        Top = 1
        Width = 220
        Height = 21
        Align = alClient
        TabOrder = 0
      end
    end
  end
  object PanelBas: TPanel
    Left = 0
    Top = 260
    Width = 354
    Height = 41
    Align = alBottom
    TabOrder = 1
    object btnUpdateProduct: TButton
      Left = 97
      Top = 6
      Width = 145
      Height = 27
      Caption = 'Mise '#224' jour du produit'
      TabOrder = 0
      OnClick = btnUpdateProductClick
    end
  end
end
