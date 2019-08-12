object UpdateUserForm: TUpdateUserForm
  Left = 0
  Top = 0
  Caption = 'UpdateUserForm'
  ClientHeight = 496
  ClientWidth = 584
  Color = clBtnFace
  Constraints.MaxHeight = 535
  Constraints.MaxWidth = 600
  Constraints.MinHeight = 535
  Constraints.MinWidth = 600
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  OnResize = FormResize
  PixelsPerInch = 96
  TextHeight = 13
  object lblTitreUpdateUser: TLabel
    Left = 0
    Top = 0
    Width = 584
    Height = 24
    Align = alTop
    Alignment = taCenter
    Caption = 'Mise '#224' jour d'#39'un utilisateur'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -20
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
    ExplicitWidth = 236
  end
  object PanelBas: TPanel
    Left = 0
    Top = 455
    Width = 584
    Height = 41
    Align = alBottom
    TabOrder = 0
    object btnMajUser: TButton
      Left = 232
      Top = 6
      Width = 145
      Height = 27
      Caption = 'Mettre '#224' jour l'#39'utilisateur'
      TabOrder = 0
      OnClick = btnMajUserClick
    end
  end
  object PanelDroit: TPanel
    Left = 295
    Top = 24
    Width = 289
    Height = 431
    Align = alRight
    TabOrder = 1
    object Panel13: TPanel
      Left = 1
      Top = 288
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 7
      object Label8: TLabel
        Left = 1
        Top = 1
        Width = 45
        Height = 39
        Align = alLeft
        Caption = 'Contact :'
        ExplicitHeight = 13
      end
      object EditContact: TEdit
        Left = 46
        Top = 1
        Width = 240
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel11: TPanel
      Left = 1
      Top = 165
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 4
      object Label9: TLabel
        Left = 1
        Top = 1
        Width = 91
        Height = 39
        Align = alLeft
        Caption = 'Type de boutique :'
        ExplicitHeight = 13
      end
      object EditshopKind: TEdit
        Left = 92
        Top = 1
        Width = 194
        Height = 39
        Align = alClient
        BiDiMode = bdLeftToRight
        Enabled = False
        ParentBiDiMode = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel10: TPanel
      Left = 1
      Top = 124
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 3
      object Label10: TLabel
        Left = 1
        Top = 1
        Width = 61
        Height = 39
        Align = alLeft
        Caption = 'Code Postal:'
        ExplicitHeight = 13
      end
      object EditPostalCode: TEdit
        Left = 62
        Top = 1
        Width = 224
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel9: TPanel
      Left = 1
      Top = 83
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 2
      object Label11: TLabel
        Left = 1
        Top = 1
        Width = 92
        Height = 39
        Align = alLeft
        Caption = 'Adresse compl'#232'te :'
        ExplicitHeight = 13
      end
      object EditShopStreet: TEdit
        Left = 93
        Top = 1
        Width = 193
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel8: TPanel
      Left = 1
      Top = 42
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 1
      object Label12: TLabel
        Left = 1
        Top = 1
        Width = 25
        Height = 39
        Align = alLeft
        Caption = 'Ville :'
        ExplicitHeight = 13
      end
      object EditShopcity: TEdit
        Left = 26
        Top = 1
        Width = 260
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel7: TPanel
      Left = 1
      Top = 1
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 0
      object Label13: TLabel
        Left = 1
        Top = 1
        Width = 99
        Height = 39
        Align = alLeft
        Caption = 'Nom de la boutique :'
        ExplicitHeight = 13
      end
      object EditShopName: TEdit
        Left = 100
        Top = 1
        Width = 186
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel12: TPanel
      Left = 1
      Top = 206
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 5
      object Label2: TLabel
        Left = 1
        Top = 1
        Width = 60
        Height = 39
        Align = alLeft
        Caption = 'Description :'
        ExplicitHeight = 13
      end
      object editshopDescription: TEdit
        Left = 61
        Top = 1
        Width = 225
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel15: TPanel
      Left = 1
      Top = 370
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 9
      object Label14: TLabel
        Left = 1
        Top = 1
        Width = 46
        Height = 39
        Align = alLeft
        Caption = 'Latitude :'
        ExplicitHeight = 13
      end
      object EditLatitude: TEdit
        Left = 47
        Top = 1
        Width = 239
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel14: TPanel
      Left = 1
      Top = 329
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 8
      object Label15: TLabel
        Left = 1
        Top = 1
        Width = 54
        Height = 39
        Align = alLeft
        Caption = 'Longitude :'
        ExplicitHeight = 13
      end
      object EditLongitude: TEdit
        Left = 55
        Top = 1
        Width = 231
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel23: TPanel
      Left = 1
      Top = 247
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 6
      object Label7: TLabel
        Left = 1
        Top = 1
        Width = 57
        Height = 39
        Align = alLeft
        Caption = 'T'#233'l'#233'phone :'
        ExplicitHeight = 13
      end
      object EditTelShop: TEdit
        Left = 58
        Top = 1
        Width = 228
        Height = 39
        Align = alClient
        Enabled = False
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
  end
  object PanelGauche: TPanel
    Left = 0
    Top = 24
    Width = 289
    Height = 431
    Align = alLeft
    TabOrder = 2
    object Panel5: TPanel
      Left = 1
      Top = 201
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 4
      object lblMail: TLabel
        Left = 1
        Top = 1
        Width = 71
        Height = 39
        Align = alLeft
        Caption = 'Mot de passe :'
        ExplicitHeight = 13
      end
      object editPassword: TEdit
        Left = 72
        Top = 1
        Width = 214
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel4: TPanel
      Left = 1
      Top = 160
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 3
      object Label1: TLabel
        Left = 1
        Top = 1
        Width = 83
        Height = 39
        Align = alLeft
        Caption = 'Nom d'#39'utilisateur:'
        ExplicitHeight = 13
      end
      object EditNickName: TEdit
        Left = 84
        Top = 1
        Width = 202
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel35: TPanel
      Left = 1
      Top = 119
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 2
      object Label3: TLabel
        Left = 1
        Top = 1
        Width = 28
        Height = 39
        Align = alLeft
        Caption = 'Nom :'
        ExplicitHeight = 13
      end
      object EditLastName: TEdit
        Left = 29
        Top = 1
        Width = 257
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel2: TPanel
      Left = 1
      Top = 78
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 1
      object Label5: TLabel
        Left = 1
        Top = 1
        Width = 43
        Height = 39
        Align = alLeft
        Caption = 'Pr'#233'nom :'
        ExplicitHeight = 13
      end
      object editFirstName: TEdit
        Left = 44
        Top = 1
        Width = 242
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel1: TPanel
      Left = 1
      Top = 37
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 0
      object Label6: TLabel
        Left = 1
        Top = 1
        Width = 25
        Height = 39
        Align = alLeft
        Caption = 'Mail :'
        ExplicitHeight = 13
      end
      object EditMail: TEdit
        Left = 26
        Top = 1
        Width = 260
        Height = 39
        Align = alClient
        TabOrder = 0
        ExplicitHeight = 21
      end
    end
    object Panel6: TPanel
      Left = 1
      Top = 242
      Width = 287
      Height = 41
      Align = alTop
      TabOrder = 5
      object lblIsSeller: TLabel
        Left = 1
        Top = 1
        Width = 116
        Height = 39
        Align = alLeft
        Alignment = taCenter
        Anchors = [akLeft, akTop, akRight, akBottom]
        AutoSize = False
        Caption = 'Poss'#232'de une boutique : '
        EllipsisPosition = epEndEllipsis
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = []
        ParentFont = False
        ExplicitTop = -3
      end
      object cbIsSeller: TCheckBox
        Left = 247
        Top = 1
        Width = 39
        Height = 39
        Align = alRight
        TabOrder = 0
        OnClick = cbIsSellerClick
      end
    end
    object Panel3: TPanel
      Left = 1
      Top = 1
      Width = 287
      Height = 36
      Align = alTop
      TabOrder = 6
      object Panel16: TPanel
        Left = 1
        Top = 1
        Width = 285
        Height = 30
        Align = alTop
        TabOrder = 0
        object lblchoixId: TLabel
          Left = 1
          Top = 1
          Width = 136
          Height = 28
          Align = alLeft
          Anchors = [akLeft, akTop, akRight, akBottom]
          AutoSize = False
          Caption = 'Identifiant de l'#39'utilisateur : '
          EllipsisPosition = epEndEllipsis
          Font.Charset = DEFAULT_CHARSET
          Font.Color = clWindowText
          Font.Height = -11
          Font.Name = 'Tahoma'
          Font.Style = []
          ParentFont = False
          ExplicitHeight = 22
        end
        object cbIdUser: TComboBox
          Left = 137
          Top = 1
          Width = 147
          Height = 21
          Align = alClient
          TabOrder = 0
        end
      end
    end
  end
end