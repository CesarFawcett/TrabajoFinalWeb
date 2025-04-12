import { ReactNode } from 'react'

type ButtonProps = {
  children: ReactNode
  variant?: 'primary' | 'secondary'
  fullWidth?: boolean
  onClick?: () => void
}

export default function Button({
  children,
  variant = 'primary',
  fullWidth = false,
  ...props
}: ButtonProps) {
  const baseClasses = 'py-3 rounded-lg transition font-medium'
  const variantClasses = {
    primary: 'bg-blue-600 text-white hover:bg-blue-700',
    secondary: 'border border-blue-600 text-blue-600 hover:bg-blue-50'
  }
  const widthClass = fullWidth ? 'w-full' : 'px-6'

  return (
    <button
      className={`${baseClasses} ${variantClasses[variant]} ${widthClass}`}
      {...props}
    >
      {children}
    </button>
  )
}